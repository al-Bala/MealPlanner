package pl.mealplanner.plangenerator.productscounter.dto;

import lombok.Builder;

@Builder
public record GroceryList(
        String name,
        float amountToUse,
        float packingMeasure,
        int nrOfPackets,
        String unit
) {
}
