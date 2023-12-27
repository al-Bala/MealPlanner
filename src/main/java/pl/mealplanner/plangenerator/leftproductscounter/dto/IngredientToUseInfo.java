package pl.mealplanner.plangenerator.leftproductscounter.dto;

import lombok.Builder;

@Builder
public record IngredientToUseInfo(
        String name,
        float packingMeasure,
        int nrOfPackets,
        float surplus,
        String unit
) {
}
