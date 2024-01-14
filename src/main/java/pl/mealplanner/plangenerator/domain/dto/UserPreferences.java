package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record UserPreferences(
        int numberOfPortions,
        String diet,
        List<IngredientDto> productsToUse,
        List<String> dislikedProducts
) {
}
