package pl.mealplanner.plangenerator.plan.dto;

import lombok.Builder;

@Builder
public record DisplayPlan(
        String dayOfWeek,
        String date,
        FirstDisplayRecipe firstDisplayRecipe
) {
}
