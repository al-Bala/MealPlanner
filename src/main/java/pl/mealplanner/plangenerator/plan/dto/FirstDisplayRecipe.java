package pl.mealplanner.plangenerator.plan.dto;

import lombok.Builder;

@Builder
public record FirstDisplayRecipe(
        String id,
        String name,
        int prepareTime
) {
}
