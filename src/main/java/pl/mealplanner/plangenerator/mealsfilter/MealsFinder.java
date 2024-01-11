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

    public FilteredRecipeDto findMatchingRecipe(InfoForFiltering info) {
        List<Recipe> allRecipes = repository.findMatchingRecipes(info);
        return getFilteredRecipesDtoList(allRecipes);
    }

    private FilteredRecipeDto getFilteredRecipesDtoList(List<Recipe> recipesDb) {
        Recipe choseRecipe = chooseOneRecipe(recipesDb);
        return convert(choseRecipe);
    }

    /**
     * TODO: Wybieranie njalepszego przepisu według składników (ilość) zamiast pierwszego w liście
     */
    private Recipe chooseOneRecipe(List<Recipe> recipesDb) {
        return recipesDb.get(0);
    }
    private FilteredRecipeDto convert(Recipe choseRecipe) {
        return MealsFilterMapper.mapFromRecipeToFilteredRecipeDto(choseRecipe);
    }
}
