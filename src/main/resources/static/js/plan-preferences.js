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
                `<div id="${fieldPrefix}-${x}">
                    <input name="${fieldPrefix}[${x}].name" type="text" placeholder="Name" class="search" onblur="fetchUnits(this.value, ${x})" required/>
                    ${fieldPrefix.includes("productsToUse") ? 
                        '<input name="' + fieldPrefix + '[' + x + '].amount" type="number" placeholder="Amount" required/>' : ''}
                     ${fieldPrefix.includes("productsToUse") ?
                        '<select id="units-'+x+'" name="' + fieldPrefix + '[' + x + '].unit" required>' +
                            '<option value="" disabled selected >Wybierz jednostkę</option>' +
                        '</select>' : ''}
                    <button type="button" class="${deleteButtonClass}">Usuń</button>
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

function fetchUnits(productName, x) {
    $.get('/meal-planner/plan/unitOptions?productName=' + productName, function (data) {
        var unitsSelect = $("#units-"+x);

        unitsSelect.empty();
        unitsSelect.append('<option value="" disabled selected >Wybierz jednostkę</option>');

        $.each(data, function (index, unit) {
            unitsSelect.append(
                '<option value="' + unit + '">' + unit + '</option>');
        });
    });
}

function initializeAutocomplete(element) {
    element.autocomplete({
        source: "productNamesAutocomplete",
        minLength: 2,
    });
}


