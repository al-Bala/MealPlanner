package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IngredientRequest {
    String name;
    float amount;
    String unit;

    public IngredientRequest(){

    }

    public IngredientRequest(String name, float amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
}
