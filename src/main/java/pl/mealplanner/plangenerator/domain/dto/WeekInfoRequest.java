package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record WeekInfoRequest(
        List<DayInfo> dayInfoList
) {
    public WeekInfoRequest(){
        this(List.of(new DayInfo()));
    }
}
