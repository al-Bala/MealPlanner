package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record OneMealInfo(
        LocalDate dayOfWeek,
        int forHowManyDays,
        int timeForPrepareMin
) {
}
