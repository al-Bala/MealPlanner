package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record InfoOneMealSearch(
        int forHowManyDays,
        String diet,
        int timeForPrepareMin,
        List<String> productsToUse,
        List<String> dislikedProducts

) {
}
