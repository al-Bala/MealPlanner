package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MealPlanElement(
        LocalDate dayOfWeek,
        ConvertedRecipe recipe
) {
}
