package pl.mealplanner.plangenerator.plan.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Optional;

@Builder
public record DisplayPlan(
        LocalDate dayOfWeek,
        DisplayRecipe recipeDisplay
) {
}
