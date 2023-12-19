package pl.mealplanner.plangenerator.leftproductscounter.dto;

import lombok.Builder;
import pl.mealplanner.plangenerator.leftproductscounter.entity.PackingMeasures;

import java.util.List;

@Builder
public record ProductDto(
        String name,
        List<PackingMeasures> packingMeasures
) {
}
