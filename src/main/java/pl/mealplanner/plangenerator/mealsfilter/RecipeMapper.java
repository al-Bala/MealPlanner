package pl.mealplanner.plangenerator.mealsfilter;

import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.ProductDto;

class RecipeMapper {
    public static FilteredRecipeDto mapFromRecipeToFilteredRecipeDto(Recipe recipe) {
        return FilteredRecipeDto.builder()
                .name(recipe.name())
                .portions(recipe.portions())
                .prepare_time(recipe.prepareTimeMin())
                .max_storage_time(recipe.maxStorageTimeDays())
                .diet(recipe.diet())
                .ingredients(recipe.ingredients().stream()
                        .map(ing -> ProductDto.builder()
                                .name(ing.name())
                                .amount(ing.amount())
                                .unit(ing.unit())
                                .build())
                        .toList())
                .build();
    }
}
