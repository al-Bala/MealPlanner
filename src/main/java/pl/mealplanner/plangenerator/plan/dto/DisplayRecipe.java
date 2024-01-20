package pl.mealplanner.plangenerator.plan.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DisplayRecipe(


        String id,
        String name,
        int portions,
        int prepare_time,
//        String diet,
        List<DisplayIngredient> ingredients,
        List<String> steps
) {

}
