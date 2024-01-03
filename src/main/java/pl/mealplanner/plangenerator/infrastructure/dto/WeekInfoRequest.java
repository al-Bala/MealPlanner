package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Data;
import pl.mealplanner.plangenerator.domain.dto.DayInfo;

import java.util.List;

@Data
public class WeekInfoRequest {
    List<DayInfoRequest> dayInfoList;

    public WeekInfoRequest(){

    }

    public WeekInfoRequest(List<DayInfoRequest> dayInfoList) {
        this.dayInfoList = dayInfoList;
    }
}
