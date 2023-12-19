package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record OneMealInfo(
        LocalDate dayOfWeek,
        int forHowManyDays,
        int timeForPrepareMin
//        List<String> productsToUse
) {
}
