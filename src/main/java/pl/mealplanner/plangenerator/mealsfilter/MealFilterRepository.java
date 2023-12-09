package pl.mealplanner.plangenerator.mealsfilter;

import org.springframework.stereotype.Repository;

import java.util.List;

interface MealFilterRepository{
//    Recipe findOneMatchingRecipe(SearchingAllMealsDto searchingAllMealsDto);
    List<String> findOneMatchingRecipe(
            int forHowManyDays,
            String diet,
            int timeForPrepareMin,
            List<String> dislikedProducts,
            List<String> productsToUse
    );
}
