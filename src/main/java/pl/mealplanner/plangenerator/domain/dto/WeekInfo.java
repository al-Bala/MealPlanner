package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record WeekInfo(
        List<DayInfo> dayInfoList
) {
}
