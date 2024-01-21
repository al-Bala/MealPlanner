

function initializeRecipeFields(wrapper, fieldPrefix, userId) {
    var fieldHtml = `<div id="${fieldPrefix}-search" class="browser__wrapper">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input name="${fieldPrefix}[0].name" type="text" placeholder="Nazwa przepisu" class="favorite-search browser__input" required/>
                     </div>`;
    $(wrapper).append(fieldHtml);
    initializeFavoriteRecipeAutocomplete($(".favorite-search"), userId);
}

function initializeFavoriteRecipeAutocomplete(searchElement, userId) {
    searchElement.autocomplete({
        source: function(request, response) {
            $.getJSON("/favoriteRecipesAutocomplete", { userId: userId, term: request.term }, function(data) {
                response($.map(data, function(item) {
                    return {
                        label: item.name,
                        value: item.id
                    };
                }));
            });
        },
        minLength: 2,
        select: function(event, ui) {
            window.location.href = '/meal-planner/recipes/' + ui.item.value;
            return false;
        }
    }).autocomplete("instance")._renderItem = function(ul, item) {
        return $("<li>")
            .append("<div>" + item.label + "</div>")
            .appendTo(ul);
    };
}