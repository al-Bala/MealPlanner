package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.util.List;
import java.util.Optional;

@Builder
public record UserPreferencesDto(
        int numberOfPortions,
        String diet,
        List<String> productsToUse,
        List<String> dislikedProducts
) {
}
