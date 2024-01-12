package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.UserPreferencesDto;
import pl.mealplanner.plangenerator.leftproductscounter.LeftProductsCounterFacade;
import pl.mealplanner.plangenerator.leftproductscounter.ListOfProductsForPlan;
import pl.mealplanner.plangenerator.leftproductscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class MealsFilterFacade {
    private final MealsFinder mealsFinder;
    private final IngredientsCalculator ingredientsCalculator;
    private final LeftProductsCounterFacade leftProductsCounterFacade;
    private final ListOfProductsForPlan listOfProductsForPlan;

    public List<FilteredRecipeDto> findRecipes(InfoForMealsSearch infoForMealsSearch) {
        List<FilteredRecipeDto> allRecipesForPlan = new ArrayList<>();
        UserPreferencesDto preferences = infoForMealsSearch.preferencesDto();
        int nrPortionsUser = preferences.numberOfPortions();

        includeUserProductsToUse(preferences.productsToUse());

        for (OneMealInfo oneMealInfo : infoForMealsSearch.oneMealInfoList()) {
            InfoForFiltering info = getInfoForFiltering(oneMealInfo, preferences, listOfProductsForPlan.getListOfProductsForPlan());
            FilteredRecipeDto matchingRecipe = findRecipe(info);
            FilteredRecipeDto recipeWithCalculatedIng = calculateIngredients(matchingRecipe, nrPortionsUser);
            allRecipesForPlan.add(recipeWithCalculatedIng);

            updateProductsToUse(recipeWithCalculatedIng);
            List<PlanProductInfo> planProductInfos = calculateLeftovers(recipeWithCalculatedIng);
            addProductsToGroceryList(planProductInfos);
        }
        return allRecipesForPlan;
    }

    private void includeUserProductsToUse(List<IngredientDto> productsToUse) {
        List<PlanProductInfo> productsToUseList = MealsFilterMapper.mapForIncludeUserProductsToUse(productsToUse);
        listOfProductsForPlan.addAll(productsToUseList);
    }

    private InfoForFiltering getInfoForFiltering(OneMealInfo oneMealInfo, UserPreferencesDto preferences, Set<PlanProductInfo> productsToUse) {
        return InfoForFiltering.builder()
                .forHowManyDays(oneMealInfo.forHowManyDays())
                .diet(preferences.diet())
                .timeForPrepareMin(oneMealInfo.timeForPrepareMin())
                .productsToUse(productsToUse)
                .dislikedProducts(preferences.dislikedProducts())
                .build();
    }

    private FilteredRecipeDto findRecipe(InfoForFiltering info) {
        return mealsFinder.findMatchingRecipe(info);
    }

    private FilteredRecipeDto calculateIngredients(FilteredRecipeDto matchingRecipe, int nrPortionsUser) {
        return ingredientsCalculator.calculateIngredients(matchingRecipe, nrPortionsUser);
    }

    private void updateProductsToUse(FilteredRecipeDto recipe) {
        List<PlanProductInfo> ingToUpdate = MealsFilterMapper.mapForUpdateAfterFoundRecipe(recipe.ingredients());
        ingToUpdate.forEach(listOfProductsForPlan::updateProductsAfterFoundRecipe);
    }

    private List<PlanProductInfo> calculateLeftovers(FilteredRecipeDto recipe) {
        return leftProductsCounterFacade.calculateProducts(recipe);
    }

    private void addProductsToGroceryList(List<PlanProductInfo> productsToUseFromLeftovers) {
        productsToUseFromLeftovers.forEach(listOfProductsForPlan::add);
    }
    
    String saveTheLastLeftovers(List<IngredientDto> productsToUse) {
        return null;
    }
}
