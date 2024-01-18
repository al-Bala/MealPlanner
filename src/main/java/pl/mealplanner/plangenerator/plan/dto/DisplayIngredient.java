package pl.mealplanner.plangenerator.plan.dto;

import lombok.Builder;

@Builder
public record DisplayIngredient(
        String name,
        float amountDisplay,
        String unitDisplay
) {
}
