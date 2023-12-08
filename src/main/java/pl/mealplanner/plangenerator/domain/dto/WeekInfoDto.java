package pl.mealplanner.plangenerator.domain.dto;

import java.util.List;

public record WeekInfoDto(
        List<DayInfo> dayInfoList
) {
}
