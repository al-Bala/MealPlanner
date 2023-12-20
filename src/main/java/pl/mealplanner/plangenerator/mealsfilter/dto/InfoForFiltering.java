package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record InfoForFiltering(
        int forHowManyDays,
        String diet,
        int timeForPrepareMin,
        List<IngredientDto> productsToUse,
        List<String> dislikedProducts
) {
}