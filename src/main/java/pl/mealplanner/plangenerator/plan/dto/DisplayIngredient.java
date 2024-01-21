package pl.mealplanner.plangenerator.plan.dto;

import lombok.Builder;

@Builder
public record DisplayIngredient(
        String name,
        float amount,
        String unit
) {
}
