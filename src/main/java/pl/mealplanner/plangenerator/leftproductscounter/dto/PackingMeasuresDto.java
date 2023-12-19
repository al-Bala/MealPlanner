package pl.mealplanner.plangenerator.leftproductscounter.dto;

import lombok.Builder;

@Builder
public record PackingMeasuresDto(
        String amount,
        String unit
) {
}
