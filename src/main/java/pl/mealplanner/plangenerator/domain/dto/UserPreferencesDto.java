package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.List;
@Builder
public record UserPreferencesDto(
        int numberOfPortions,
        String diet,
        List<IngredientDto> productsToUse,
        List<String> dislikedProducts
) {
}
