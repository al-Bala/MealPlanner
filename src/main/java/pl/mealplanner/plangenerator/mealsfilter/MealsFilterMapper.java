package pl.mealplanner.plangenerator.mealsfilter;

import pl.mealplanner.plangenerator.leftproductscounter.dto.Leftover;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.List;

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


    public static List<String> mapFromListIngredientDtoToListString(List<IngredientDto> productsToUse) {
        return productsToUse.stream()
                .map(IngredientDto::name)
                .toList();
    }

    public static List<IngredientDto> mapFromLeftoverToIngredientDto(List<Leftover> leftovers) {
        return leftovers.stream()
                .map(l -> new IngredientDto(l.name(), l.surplus(), l.unit()))
                .toList();
    }
}
