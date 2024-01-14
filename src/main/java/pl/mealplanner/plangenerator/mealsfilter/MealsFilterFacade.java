package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.dto.MatchingRecipe;

@AllArgsConstructor
@Component
public class MealsFilterFacade {
    private final MealsFinder mealsFinder;
//    private final IngredientsCalculator ingredientsCalculator;
//    private final LeftProductsCounterFacade leftProductsCounterFacade;
//    private final ListOfProductsForPlan listOfProductsForPlan;
//    private final UnitConverterFacade unitConverterFacade;

//    public List<ConvertedRecipe> generatePlan(InfoForMealsSearch infoForMealsSearch) {
//        List<ConvertedRecipe> allRecipesForPlan = new ArrayList<>();
//        UserPreferencesDto preferences = infoForMealsSearch.preferencesDto();
//        int nrPortionsUser = preferences.numberOfPortions();
//
//        includeUserProductsToUse(preferences.productsToUse());
//
//        for (OneMealInfo oneMealInfo : infoForMealsSearch.oneMealInfoList()) {
//            InfoForFiltering info = getInfoForFiltering(oneMealInfo, preferences, listOfProductsForPlan.getListOfProductsForPlan());
//            MatchingRecipe matchingRecipe = findRecipe(info);
//            ConvertedRecipe convertedRecipe = convertIngsFromRecipeToMainUnit(matchingRecipe);
////            ConvertedRecipe convertedRecipe = unitConverterFacade.convertIngsFromRecipeToMainUnit(matchingRecipe);
//            ConvertedRecipe recipeWithCalculatedIngs = calculateIngredients(convertedRecipe, nrPortionsUser);
//            allRecipesForPlan.add(recipeWithCalculatedIngs);
//
////            updateProductsToUse(ingConverted);
//            List<PlanProductInfo> productsForPlan = choosePacketAndCalculateLeftovers(recipeWithCalculatedIngs.ingredients());
//            addProductsToGroceryList(productsForPlan);
//        }
//        return allRecipesForPlan;
//    }

//    private void includeUserProductsToUse(List<IngredientDto> productsToUse) {
//        List<IngredientConverted> convertedProducts = unitConverterFacade.convertProductsToUseToMainUnit(productsToUse, "withoutDisplay");
//        List<PlanProductInfo> productsToUseList = MealsFilterMapper.mapForIncludeUserProductsToUse(convertedProducts);
//        listOfProductsForPlan.addAll(productsToUseList);
//    }

//    private InfoForFiltering getInfoForFiltering(OneMealInfo oneMealInfo, UserPreferencesDto preferences, Set<PlanProductInfo> productsToUse) {
//        return InfoForFiltering.builder()
//                .forHowManyDays(oneMealInfo.forHowManyDays())
//                .diet(preferences.diet())
//                .timeForPrepareMin(oneMealInfo.timeForPrepareMin())
//                .productsToUse(productsToUse)
//                .dislikedProducts(preferences.dislikedProducts())
//                .build();
//    }

    public MatchingRecipe findRecipe(InfoForFiltering info) {
        return mealsFinder.findMatchingRecipe(info);
    }



//    private void updateProductsToUse(List<IngredientConverted> ingredients) {
//        List<PlanProductInfo> ingToUpdate = MealsFilterMapper.mapForUpdateAfterFoundRecipe(ingredients);
//        ingToUpdate.forEach(listOfProductsForPlan::updateProductsAfterFoundRecipe);
////        System.out.println(ingToUpdate);
//    }
//    private ConvertedRecipe convertIngsFromRecipeToMainUnit(MatchingRecipe matchingRecipe) {
//        return unitConverterFacade.convertIngsFromRecipeToMainUnit(matchingRecipe);
//    }

//    public ConvertedRecipe calculateIngredients(ConvertedRecipe convertedRecipe, int nrPortionsUser) {
//        return ingredientsCalculator.calculateIngredients(convertedRecipe, nrPortionsUser);
//    }

//    private List<PlanProductInfo> choosePacketAndCalculateLeftovers(List<IngredientConverted> ingredients) {
//        return leftProductsCounterFacade.choosePacketAndCalculateLeftovers(ingredients);
//    }
//
//    private void addProductsToGroceryList(List<PlanProductInfo> productsToUseFromLeftovers) {
//        productsToUseFromLeftovers.forEach(listOfProductsForPlan::add);
//    }

//    String saveTheLastLeftovers(List<IngredientDto> productsToUse) {
//        return null;
//    }
}
