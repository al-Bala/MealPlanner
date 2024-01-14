package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;
import pl.mealplanner.plangenerator.unitconverter.DisplayCountAU;

@Builder
public record IngredientConverted(
        String name,
        DisplayCountAU amountsAndUnit
) {
}
