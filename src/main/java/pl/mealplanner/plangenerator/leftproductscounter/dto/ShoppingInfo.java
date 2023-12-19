package pl.mealplanner.plangenerator.leftproductscounter.dto;

import lombok.Builder;

@Builder
public record ShoppingInfo(
        float packingMeasure,
        int nrOfPackets,
        float surplus
) {
}
