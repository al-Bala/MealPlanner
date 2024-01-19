package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Optional;

@Builder
public record MealPlanElement(
        LocalDate dayOfWeek,
        ConvertedRecipe recipe
) {
}
