package pl.mealplanner.plangenerator.leftproductscounter.dto;

import lombok.Builder;

@Builder
public record IngredientsToUseInfo (
        String name,
        float packingMeasure,
        int nrOfPackets,
        float surplus,
        String unit
) {
}
