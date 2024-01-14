package pl.mealplanner.plangenerator.mealsfilter;

import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.mealsfilter.dto.MatchingRecipe;
import pl.mealplanner.plangenerator.domain.dto.IngredientDto;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.Collections;
import java.util.List;
import java.util.Set;

class MealsFilterMapper {
    public static MatchingRecipe mapFromRecipeToFilteredRecipeDto(Recipe recipe) {
        return MatchingRecipe.builder()
                .name(recipe.name())
                .portions(recipe.portions())
                .prepare_time(recipe.prepareTimeMin())
                .max_storage_time(recipe.maxStorageTimeDays())
                .diet(recipe.diet())
                .ingredients(recipe.ingredients().stream()
                        .map(ing -> IngredientDto.builder()
                                .name(ing.name())
                                .amount(ing.amount())
                                .unit(ing.unit())
                                .build())
                        .toList())
                .build();
    }


    public static List<String> mapFromPlanProductInfoToListString(Set<PlanProductInfo> productsToUse) {
        if(productsToUse == null){
            return Collections.emptyList();
        }
        return productsToUse.stream()
                .map(PlanProductInfo::getName)
                .toList();
    }

//    public static List<PlanProductInfo> mapForIncludeUserProductsToUse(List<IngredientConverted> productsToUse) {
//        // ustawia userProductsToUse jako nadwyżka do wykorzystania
//        return productsToUse.stream()
//                .map(p -> PlanProductInfo.builder()
//                        .name(p.name())
////                        .amountToUseDisplay(0)
//                        .amountToUseCount(0)
//                        .packingMeasure(0)
//                        .nrOfPackets(0)
//                        .surplus(p.amountsAndUnit().getAmountCount())
////                        .unitDisplay(p.amountsAndUnit().getUnitDisplay())
//                        .unitCount(p.amountsAndUnit().getUnitCount())
//                        .build())
//                .toList();
//    }

//    public static List<PlanProductInfo> mapForUpdateAfterFoundRecipe(List<IngredientConverted> ingFromRecipe) {
//        // ustawia składniki z przepisu jako produkty do użycia == kupienia
//        return ingFromRecipe.stream()
//                .map(i -> PlanProductInfo.builder()
//                        .name(i.name())
////                        .amountToUseDisplay(i.amountsAndUnit().getAmountDisplay())
//                        .amountToUseCount(i.amountsAndUnit().getAmountCount())
//                        .packingMeasure(0)
//                        .nrOfPackets(0)
//                        .surplus(0)
////                        .unitDisplay(i.amountsAndUnit().getUnitDisplay())
//                        .unitCount(i.amountsAndUnit().getUnitCount())
//                        .build())
//                .toList();
//    }
}
