package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Data;
import pl.mealplanner.plangenerator.domain.dto.Eating;

import java.time.LocalDate;

@Data
public class DayInfoRequest {
    LocalDate day;
    Eating eatingPlan;

    public DayInfoRequest(){

    }

    public DayInfoRequest(Eating eatingPlan) {
        this.eatingPlan = eatingPlan;
    }
}
