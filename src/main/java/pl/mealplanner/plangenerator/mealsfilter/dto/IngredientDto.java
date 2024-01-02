package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;

@Builder
public record IngredientDto(
        String name,
        float amount,
        String unit
) {
    public IngredientDto(){
        this("", 0, "");
    }
}
