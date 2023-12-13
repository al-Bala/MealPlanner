package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.List;

@AllArgsConstructor
@Component
class MealsFinder {

    private final MealsFilterRepository repository;

    public List<FilteredRecipeDto> findMatchingRecipes(InfoForFiltering info) {
        List<Recipe> list = repository.findMatchingRecipes(info.forHowManyDays(), info.diet(), info.timeForPrepareMin(), info.productsToUse(), info.dislikedProducts());
        return getFilteredRecipesDtoList(list);
    }

    private List<FilteredRecipeDto> getFilteredRecipesDtoList(List<Recipe> recipesDb) {
        List<Recipe> choseRecipes = choseMaxFirst10(recipesDb);
        return choseRecipes.stream()
                .map(this::convert)
                .toList();
    }
    private List<Recipe> choseMaxFirst10(List<Recipe> recipesDb) {
        if(recipesDb.size() > 10){
            return recipesDb.subList(0,10);
        } else {
            return recipesDb;
        }
    }
    private FilteredRecipeDto convert(Recipe choseRecipe) {
        return RecipeMapper.mapFromRecipeToFilteredRecipeDto(choseRecipe);
    }
}
