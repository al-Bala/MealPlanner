package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class WeekInfoRequest {
    List<DayInfoRequest> dayInfoList;

    public WeekInfoRequest(){

    }

    public WeekInfoRequest(List<DayInfoRequest> dayInfoList) {
        this.dayInfoList = dayInfoList;
    }
}
