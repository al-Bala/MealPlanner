package pl.mealplanner.plangenerator.productscounter.dto;

import lombok.Builder;

@Builder
public record MainIngToUseInfo(
        float packingMeasure,
        int nrOfPackets,
        float surplus
) {
}
