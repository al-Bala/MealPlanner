package pl.mealplanner.plangenerator.leftproductscounter.dto;

import lombok.Builder;

@Builder
public record Leftover(
        String name,
        float surplus,
        String unit
) {
}
