package pl.mealplanner.plangenerator.mealsfilter;

import pl.mealplanner.plangenerator.leftproductscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.Collections;
import java.util.List;
import java.util.Set;

class MealsFilterMapper {
    public static FilteredRecipeDto mapFromRecipeToFilteredRecipeDto(Recipe recipe) {
        return FilteredRecipeDto.builder()
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

    public static List<PlanProductInfo> mapForIncludeUserProductsToUse(List<IngredientDto> productsToUse) {
        // ustawia userProductsToUse jako nadwyżka do wykorzystania
        return productsToUse.stream()
                .map(p -> PlanProductInfo.builder()
                        .name(p.name())
                        .amountToUse(0)
                        .packingMeasure(0)
                        .nrOfPackets(0)
                        .surplus(p.amount())
                        .unit(p.unit())
                        .build())
                .toList();
    }

    public static List<PlanProductInfo> mapForUpdateAfterFoundRecipe(List<IngredientDto> ingFromRecipe) {
        // ustawia składniki z przepisu jako produkty do użycia == kupienia
        return ingFromRecipe.stream()
                .map(i -> PlanProductInfo.builder()
                        .name(i.name())
                        .amountToUse(i.amount())
                        .packingMeasure(0)
                        .nrOfPackets(0)
                        .surplus(0)
                        .unit(i.unit())
                        .build())
                .toList();
    }
}
