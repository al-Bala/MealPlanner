function initializeRecipeFields(wrapper, fieldPrefix) {
    var fieldHtml = `<div id="${fieldPrefix}-search" class="browser__wrapper">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input name="${fieldPrefix}[0].name" type="text" placeholder="Nazwa przepisu" class="search browser__input" required/>
                    </div>`;
    $(wrapper).append(fieldHtml);
    initializeAutocomplete($(".search"));
}

function initializeAutocomplete(search) {
    search.autocomplete({
        source: "recipeNamesAutocomplete",
        minLength: 2,
        select: function(event, ui) {
            window.location.href = '/meal-planner/recipes/' + ui.item.id;
            return false;
        }
    }).autocomplete("instance")._renderItem = function(ul, item) {
        return $("<li>")
            .append("<div>" + item.name + "<span style='display:none;'>" + item.id + "</span></div>")
            .appendTo(ul);
    };
}

$(document).ready(function () {
    // Inicjalizacja wyszukiwarki przepisów
    initializeRecipeFields('#recipe-search-wrapper', 'recipe');
});

function removeDefaultOption(selectElement) {
    selectElement.options[0].disabled = true;

    // Sprawdź, czy wybrano opcję inną niż domyślna
    if (selectElement.selectedIndex > 0) {
        // Aktywuj przycisk "Losuj"
        document.getElementById("submit-button").disabled = false;
    } else {
        // Zablokuj przycisk "Losuj"
        document.getElementById("submit-button").disabled = true;
    }
}