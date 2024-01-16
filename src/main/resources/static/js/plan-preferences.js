$(document).ready(function() {
    initializeProductFields($(".productsU"), $(".add-productU"), "preferences.productsToUse", "deleteU");
    initializeProductFields($(".productsD"), $(".add-productD"), "preferences.dislikedProducts", "deleteD");
});

function initializeProductFields(wrapper, addButton, fieldPrefix, deleteButtonClass) {
    var max_fields = 30;
    var x = 0;

    $(addButton).click(function(e) {
        e.preventDefault();
        if (x < max_fields) {
            var fieldHtml =
                `<div id="${fieldPrefix}-${x}" class="products__container">
                    <div class="test">
                        <label class="products__label products__label--1">Nazwa produktu</label>
                        <input name="${fieldPrefix}[${x}].name" type="text" placeholder="Wpisz nazwę produktu" class="search product__field"/>
                    </div>
                    
                    <div class="products__wrapper">
                        <div class="test">
                            <label class="products__label products__label--2">Ilość</label>
                            ${fieldPrefix.includes("productsToUse") ? '<input name="' + fieldPrefix + '[' + x + '].amount" type="number" min="0" placeholder="Wpisz ilość" class="amount product__field"/>' : ''}
                        </div>
                            
                        <div class="test">
                            <label class="products__label products__label--2">Jednostka</label>
                            ${fieldPrefix.includes("productsToUse") ? '<input name="' + fieldPrefix + '[' + x + '].unit" type="text" placeholder="Wybierz jednostkę" class="unit product__field"/>' : ''}
                        </div>                                        
                    </div>
                    <button type="button" title="Usuń produkt" class="${deleteButtonClass}"><i class="fa-solid fa-trash-can-arrow-up"></i></button>
                </div>`;

            $(wrapper).append(fieldHtml);
            initializeAutocomplete($(".search"));
            x++;
        }
    });

    deleteProduct(wrapper, deleteButtonClass, x, fieldPrefix);
}

function deleteProduct(wrapper, deleteButtonClass, x, fieldPrefix) {
    $(wrapper).on("click", "." + deleteButtonClass, function(e) {
        e.preventDefault();
        $(this).parent('div').remove();
        updateIndexes(wrapper, fieldPrefix);
        x--;
    });
}

function updateIndexes(wrapper, fieldPrefix) {
    $(wrapper).find('div').each(function(index, element) {
        var newIndex = index + 1;
        $(element).attr('id', `${fieldPrefix}-${newIndex}`);
        $(element).find('input').each(function(inputIndex, inputElement) {
            $(inputElement).attr('name', `${fieldPrefix}[${newIndex}].${inputIndex === 0 ? 'name' : inputIndex === 1 ? 'amount' : 'unit'}`);
        });
    });
}

function initializeAutocomplete(element) {
    element.autocomplete({
        source: "productNamesAutocomplete",
        minLength: 2,
    });
}


