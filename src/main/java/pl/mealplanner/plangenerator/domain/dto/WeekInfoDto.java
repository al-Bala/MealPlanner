package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record WeekInfoDto(
        List<DayInfo> dayInfoList
) {
}
