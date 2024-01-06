package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPreferencesRequest {
    int numberOfPortions;
    String diet;
    List<IngredientRequest> productsToUse;
    List<DislikedProductRequest> dislikedProducts;

    public UserPreferencesRequest(){
    }

    public UserPreferencesRequest(List<IngredientRequest> productsToUse, List<DislikedProductRequest> dislikedProducts) {
        this.productsToUse = productsToUse;
        this.dislikedProducts = dislikedProducts;
    }
}
