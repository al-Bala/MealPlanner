package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.UserPreferences;
import pl.mealplanner.plangenerator.mealsfilter.MealsFilterFacade;
import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientConverted;
import pl.mealplanner.plangenerator.mealsfilter.dto.MatchingRecipe;
import pl.mealplanner.plangenerator.productscounter.ProductsCounterFacade;
import pl.mealplanner.plangenerator.productscounter.ListOfProductsForPlan;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.unitconverter.UnitConverterFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class PlanGeneratorService {
    private final UnitConverterFacade unitConverterFacade;
    private final ListOfProductsForPlan listOfProductsForPlan;
    private final MealsFilterFacade mealsFilterFacade;
    private final ProductsCounterFacade productsCounterFacade;
    public static List<ConvertedRecipe> allRecipesForPlan = new ArrayList<>();


    public List<ConvertedRecipe> generatePlan(InfoForMealsSearch infoForMealsSearch) {
        UserPreferences preferences = infoForMealsSearch.preferencesDto();
        int nrPortionsUser = preferences.numberOfPortions();

        unitConverterFacade.includeUserProductsToUse(preferences.productsToUse());

        for (OneMealInfo oneMealInfo : infoForMealsSearch.oneMealInfoList()) {
            InfoForFiltering info = getInfoForFiltering(oneMealInfo, preferences, listOfProductsForPlan.getListOfProductsForPlan());
            MatchingRecipe matchingRecipe = findRecipe(info);
            ConvertedRecipe convertedRecipe = convertRecipeIngsToMainUnit(matchingRecipe);
            ConvertedRecipe recipeWithCalculatedIngs = calculateIngredients(convertedRecipe, nrPortionsUser);
            allRecipesForPlan.add(recipeWithCalculatedIngs);

            List<PlanProductInfo> productsForPlan = choosePacketsAndCalculateLeftovers(recipeWithCalculatedIngs.ingredients());
            addProductsToGroceryList(productsForPlan);
        }
        return allRecipesForPlan;
    }

    private InfoForFiltering getInfoForFiltering(OneMealInfo oneMealInfo, UserPreferences preferences, Set<PlanProductInfo> productsToUse) {
        List<String> productNames = productsToUse.stream()
                .map(PlanProductInfo::getName)
                .toList();

        return InfoForFiltering.builder()
                .forHowManyDays(oneMealInfo.forHowManyDays())
                .diet(preferences.diet())
                .timeForPrepareMin(oneMealInfo.timeForPrepareMin())
                .productsToUse(productNames)
                .dislikedProducts(preferences.dislikedProducts())
                .build();
    }

    private MatchingRecipe findRecipe(InfoForFiltering info) {
        return mealsFilterFacade.findRecipe(info);
    }

    private ConvertedRecipe convertRecipeIngsToMainUnit(MatchingRecipe matchingRecipe) {
        return unitConverterFacade.convertIngsFromRecipeToMainUnit(matchingRecipe);
    }

    private ConvertedRecipe calculateIngredients(ConvertedRecipe convertedRecipe, int nrPortionsUser) {
        return productsCounterFacade.calculateIngredients(convertedRecipe, nrPortionsUser);
    }

    private List<PlanProductInfo> choosePacketsAndCalculateLeftovers(List<IngredientConverted> ingredients) {
        return productsCounterFacade.choosePacketsAndCountLeftovers(ingredients);
    }

    private void addProductsToGroceryList(List<PlanProductInfo> productsToUseFromLeftovers) {
        productsToUseFromLeftovers.forEach(listOfProductsForPlan::add);
    }

}
