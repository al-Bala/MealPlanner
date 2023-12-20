package pl.mealplanner.plangenerator.leftproductscounter.dto;

import lombok.Builder;

@Builder
public record ShoppingInfo(
        String name,
        float packingMeasure,
        int nrOfPackets,
        String unit
) {
}
