package pl.mealplanner.plangenerator.mealsfilter;

import pl.mealplanner.plangenerator.domain.dto.IngredientDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.MatchingRecipe;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

class MealsFilterMapper {
    public static MatchingRecipe mapFromRecipeToMatchingRecipe(Recipe recipe) {
        return MatchingRecipe.builder()
                .id(recipe.id())
                .name(recipe.name())
                .portions(recipe.portions())
                .prepare_time(recipe.prepareTimeMin())
                .diet(recipe.diet())
                .ingredients(recipe.ingredients().stream()
                        .map(ing -> IngredientDto.builder()
                                .name(ing.name())
                                .amount(ing.amount())
                                .unit(ing.unit())
                                .build())
                        .toList())
                .steps(recipe.steps())
                .build();
    }
}
