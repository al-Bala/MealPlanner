package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Data;

@Data
public class IngredientRequest {
    String name;
    float amount;
    String unit;

    public IngredientRequest(){

    }
}
