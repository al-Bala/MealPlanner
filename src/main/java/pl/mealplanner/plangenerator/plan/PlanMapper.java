package pl.mealplanner.plangenerator.plan;

import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientConverted;
import pl.mealplanner.profile.domain.entity.IngredientPlanHistory;
import pl.mealplanner.profile.domain.entity.RecipeInPlanHistory;

import java.util.List;

import static pl.mealplanner.plangenerator.plan.PlanFacade.EMPTY_DAY_ID;

class PlanMapper {

    public static RecipeInPlanHistory mapFromConvertedRecipeToRecipeInPlanHistory(ConvertedRecipe convertedRecipe) {
        if(convertedRecipe.id().equals(EMPTY_DAY_ID)){
            return RecipeInPlanHistory.builder()
                    .id(EMPTY_DAY_ID)
                    .build();
        }
        return RecipeInPlanHistory.builder()
                .id(convertedRecipe.id())
                .name(convertedRecipe.name())
                .portions(convertedRecipe.portions())
                .prepareTime(convertedRecipe.prepare_time())
                .diet(convertedRecipe.diet())
                .ingredients(mapFromIngredientConvertedToIngredientPlanHistory(convertedRecipe.ingredients()))
                .steps(convertedRecipe.steps())
                .build();
    }
    private static List<IngredientPlanHistory> mapFromIngredientConvertedToIngredientPlanHistory(List<IngredientConverted> ingConverted) {
        return ingConverted.stream()
                .map(ing -> IngredientPlanHistory.builder()
                        .name(ing.name())
                        .amount(ing.amountsAndUnit().getAmountDisplay())
                        .unit(ing.amountsAndUnit().getUnitDisplay())
                        .build())
                .toList();
    }
}
