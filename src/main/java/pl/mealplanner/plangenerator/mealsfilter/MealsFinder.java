package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.dto.MatchingRecipe;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static pl.mealplanner.plangenerator.domain.PlanGeneratorService.allRecipesForPlan;

@AllArgsConstructor
@Component
class MealsFinder {

    private final MealsFilterRepository repository;

    public MatchingRecipe findMatchingRecipe(InfoForFiltering info, int limit) {
        Recipe choseRecipe = chooseOneRecipe(info, limit);
        return convert(choseRecipe);
    }

    /**
     * TODO: 1) Wybieranie njalepszego przepisu według składników (ilość) zamiast pierwszego w liście
     * TODO: 1.a) Przeliczanie składników == productsToUse na odpowiednią ilość porcji
     */

    // Sprawdzanie czy przepis nie powtarza sie w aktualnym planie
    private Recipe chooseOneRecipe(InfoForFiltering info, int limit) {
        List<Recipe> recipesDb = new ArrayList<>(repository.findMatchingRecipes(info, limit));
        Random random = new Random();

        while (!recipesDb.isEmpty()) {
            int index = random.nextInt(recipesDb.size());
            Recipe drewRecipe = recipesDb.get(index);

            if (isRepeated(drewRecipe)) {
                recipesDb.remove(index);
                break;
            }
            return drewRecipe;
        }
        return chooseOneRecipe(info, limit+1);
    }

    private boolean isRepeated(Recipe recipeToCheck) {
        if(recipeToCheck.id() == null) return true;
        return allRecipesForPlan.stream()
                .anyMatch(r -> r.recipe().id().equals(recipeToCheck.id()));
    }
    private MatchingRecipe convert(Recipe choseRecipe) {
        return MealsFilterMapper.mapFromRecipeToMatchingRecipe(choseRecipe);
    }


}
