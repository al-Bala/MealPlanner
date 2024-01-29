package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.UserPreferences;
import pl.mealplanner.plangenerator.mealsfilter.MealsFilterFacade;
import pl.mealplanner.plangenerator.mealsfilter.dto.*;
import pl.mealplanner.plangenerator.productscounter.ListOfProductsForPlan;
import pl.mealplanner.plangenerator.productscounter.ProductsCounterFacade;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.unitconverter.UnitConverterFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static pl.mealplanner.plangenerator.domain.PlanGeneratorFacade.allRecipesForPlan;
import static pl.mealplanner.plangenerator.plan.PlanFacade.EMPTY_DAY_ID;

@AllArgsConstructor
@Service
class PlanGeneratorService {
    private final UnitConverterFacade unitConverterFacade;
    private final ListOfProductsForPlan listOfProductsForPlan;
    private final MealsFilterFacade mealsFilterFacade;
    private final ProductsCounterFacade productsCounterFacade;

    public List<MealPlanElement> generatePlan(InfoForMealsSearch infoForMealsSearch) {
        allRecipesForPlan.clear();
        List<MealPlanElement> plan = new ArrayList<>();
        UserPreferences preferences = infoForMealsSearch.preferencesDto();
        int nrPortionsUser = preferences.numberOfPortions();

        unitConverterFacade.includeUserProductsToUse(preferences.productsToUse());

        for (OneMealInfo oneMealInfo : infoForMealsSearch.oneMealInfoList()) {
            if(oneMealInfo.forHowManyDays() == 0){
                MealPlanElement emptyElement = MealPlanElement.builder()
                        .dayOfWeek(oneMealInfo.dayOfWeek())
                        .recipe(ConvertedRecipe.builder().id(EMPTY_DAY_ID).build())
                        .build();
                plan.add(emptyElement);
                continue;
            }
            InfoForFiltering info = getInfoForFiltering(oneMealInfo, preferences, listOfProductsForPlan.getListOfProductsForPlan());
            MatchingRecipe matchingRecipe = findRecipe(info);
            ConvertedRecipe convertedRecipe = convertRecipeIngsToMainUnit(matchingRecipe);
            ConvertedRecipe recipeWithCalculatedIngs = calculateIngredients(convertedRecipe, nrPortionsUser, info.forHowManyDays());
            allRecipesForPlan.add(recipeWithCalculatedIngs);

            MealPlanElement recipePlan = MealPlanElement.builder()
                    .dayOfWeek(oneMealInfo.dayOfWeek())
                    .recipe(recipeWithCalculatedIngs)
                    .build();
            plan.add(recipePlan);

            List<PlanProductInfo> productsForPlan = choosePacketsAndCalculateLeftovers(recipeWithCalculatedIngs.ingredients());
            addProductsToGroceryList(productsForPlan);
        }
        return plan;
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

    private ConvertedRecipe calculateIngredients(ConvertedRecipe convertedRecipe, int nrPortionsUser, int forHowManyDays) {
        return productsCounterFacade.calculateIngredients(convertedRecipe, nrPortionsUser, forHowManyDays);
    }

    private List<PlanProductInfo> choosePacketsAndCalculateLeftovers(List<IngredientConverted> ingredients) {
        List<PlanProductInfo> ingsFromRecipe = ingredients.stream()
                .map(ing -> PlanProductInfo.builder()
                        .name(ing.name())
                        .amountToUseCount(ing.amountsAndUnit().getAmountCount())
                        .packingMeasure(0)
                        .nrOfPackets(0)
                        .surplus(0)
                        .unitCount(ing.amountsAndUnit().getUnitCount())
                        .build())
                .toList();

        ingsFromRecipe.forEach(listOfProductsForPlan::updateAfterChoseRecipe);
        return productsCounterFacade.choosePacketsAndCountLeftovers(ingsFromRecipe);
    }

    private void addProductsToGroceryList(List<PlanProductInfo> productsToUseFromLeftovers) {
        productsToUseFromLeftovers.forEach(listOfProductsForPlan::addToPlan);
    }
}
