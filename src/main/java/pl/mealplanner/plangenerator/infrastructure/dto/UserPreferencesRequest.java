package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserPreferencesRequest {
    int numberOfPortions;
    String diet;
    List<IngredientRequest> productsToUse;
    List<DislikedProductRequest> dislikedProducts;

    public UserPreferencesRequest(){
    }

    public UserPreferencesRequest(int numberOfPortions, String diet, List<IngredientRequest> productsToUse, List<DislikedProductRequest> dislikedProducts) {
        this.numberOfPortions = numberOfPortions;
        this.diet = diet;
        this.productsToUse = productsToUse;
        this.dislikedProducts = dislikedProducts;
    }

    public UserPreferencesRequest(List<IngredientRequest> productsToUse, List<DislikedProductRequest> dislikedProducts) {
        this.productsToUse = productsToUse;
        this.dislikedProducts = dislikedProducts;
    }
}
