package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;
import pl.mealplanner.plangenerator.leftproductscounter.dto.ProductDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.List;
import java.util.Optional;

@Builder
public record UserPreferencesDto(
        int numberOfPortions,
        String diet,
        List<IngredientDto> productsToUse,
        List<String> dislikedProducts
) {
}
