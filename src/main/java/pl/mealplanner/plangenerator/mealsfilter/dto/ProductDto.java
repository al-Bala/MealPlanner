package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;

@Builder
public record ProductDto(
        String name,
        float amount,
        String unit
) {
}
