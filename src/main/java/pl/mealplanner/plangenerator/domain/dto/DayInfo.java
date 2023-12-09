package pl.mealplanner.plangenerator.domain.dto;

import java.time.LocalDate;

public record DayInfo(
        LocalDate day,
        EatingPlans eatingPlans
) {
}
