package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

@Builder
public record IngredientDto(
        String name,
        float amount,
        String unit
) {
}
