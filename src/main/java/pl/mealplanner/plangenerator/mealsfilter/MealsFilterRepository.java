package pl.mealplanner.plangenerator.mealsfilter;

import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.List;

interface MealsFilterRepository {
    FilteredRecipeDto findOneMatchingRecipe(
            int forHowManyDays,
            String diet,
            int timeForPrepareMin,
            List<String> dislikedProducts,
            List<String> productsToUse
    );
}
