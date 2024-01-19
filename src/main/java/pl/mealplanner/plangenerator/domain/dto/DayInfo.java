package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DayInfo(
        LocalDate day,
        EatingPlans eatingPlan
) {
}
