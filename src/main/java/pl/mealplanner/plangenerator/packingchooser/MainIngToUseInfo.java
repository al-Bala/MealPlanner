package pl.mealplanner.plangenerator.packingchooser;

import lombok.Builder;

@Builder
public record MainIngToUseInfo(
        float packingMeasure,
        int nrOfPackets,
        float surplus
) {
}
