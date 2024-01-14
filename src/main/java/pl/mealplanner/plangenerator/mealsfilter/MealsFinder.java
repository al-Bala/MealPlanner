package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.MatchingRecipe;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.List;

@AllArgsConstructor
@Component
class MealsFinder {

    private final MealsFilterRepository repository;

    public MatchingRecipe findMatchingRecipe(InfoForFiltering info) {
        List<Recipe> allRecipes = repository.findMatchingRecipes(info);
        return getFilteredRecipesDtoList(allRecipes);
    }

    private MatchingRecipe getFilteredRecipesDtoList(List<Recipe> recipesDb) {
        Recipe choseRecipe = chooseOneRecipe(recipesDb);
        return convert(choseRecipe);
    }

    /**
     * TODO: 1) Wybieranie njalepszego przepisu według składników (ilość) zamiast pierwszego w liście
     * TODO: 1.a) Przeliczanie składników == productsToUse na odpowiednią ilość porcji
     */
    private Recipe chooseOneRecipe(List<Recipe> recipesDb) {
        return recipesDb.get(0);
    }
    private MatchingRecipe convert(Recipe choseRecipe) {
        return MealsFilterMapper.mapFromRecipeToFilteredRecipeDto(choseRecipe);
    }
}
