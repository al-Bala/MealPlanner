package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.UserPreferencesDto;
import pl.mealplanner.plangenerator.leftproductscounter.LeftProductsCounterFacade;
import pl.mealplanner.plangenerator.leftproductscounter.dto.Leftover;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class MealsFilterFacade {
    private final MealsFinder mealsFinder;
    private final IngredientsCalculator ingredientsCalculator;
    private final LeftProductsCounterFacade leftProductsCounterFacade;

    public List<FilteredRecipeDto> findRecipes(InfoForMealsSearch infoForMealsSearch) {
        UserPreferencesDto preferences = infoForMealsSearch.preferencesDto();
        int nrPortionsUser = preferences.numberOfPortions();
        List<IngredientDto> productsToUse = new ArrayList<>(preferences.productsToUse());
        List<FilteredRecipeDto> allRecipesForPlan = new ArrayList<>();

        for (OneMealInfo oneMealInfo : infoForMealsSearch.oneMealInfoList()) {
            InfoForFiltering info = getInfoForFiltering(oneMealInfo, preferences, productsToUse);
            FilteredRecipeDto recipe = getFilteredRecipe(info, nrPortionsUser);
            allRecipesForPlan.add(recipe);
            calculateLeftoversAndAddProductsToGroceryList(recipe, productsToUse);
        }
        return allRecipesForPlan;
    }

    private InfoForFiltering getInfoForFiltering(OneMealInfo oneMealInfo, UserPreferencesDto preferences, List<IngredientDto> productsToUse) {
        return InfoForFiltering.builder()
                .forHowManyDays(oneMealInfo.forHowManyDays())
                .diet(preferences.diet())
                .timeForPrepareMin(oneMealInfo.timeForPrepareMin())
                .productsToUse(productsToUse)
                .dislikedProducts(preferences.dislikedProducts())
                .build();
    }

    private FilteredRecipeDto getFilteredRecipe(InfoForFiltering info, int nrPortionsUser) {
        FilteredRecipeDto matchingRecipe = mealsFinder.findMatchingRecipe(info);
        return ingredientsCalculator.calculateIngredients(matchingRecipe, nrPortionsUser);
    }

    private void calculateLeftoversAndAddProductsToGroceryList(FilteredRecipeDto recipe, List<IngredientDto> productsToUse) {
        productsToUse.clear();
        List<Leftover> leftovers = leftProductsCounterFacade.calculateProducts(recipe);
        List<IngredientDto> productsToUseFromLeftovers = MealsFilterMapper.mapFromLeftoverToIngredientDto(leftovers);
        productsToUse.addAll(productsToUseFromLeftovers);
    }

    String saveTheLastLeftovers(List<IngredientDto> productsToUse) {
        return null;
    }
}
