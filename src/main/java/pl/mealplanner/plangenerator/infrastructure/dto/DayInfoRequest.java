package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DayInfoRequest {
    LocalDate day;
    EatingPlansRequest eatingPlan;

    public DayInfoRequest(){

    }

    public DayInfoRequest(LocalDate day, EatingPlansRequest eatingPlan) {
        this.day = day;
        this.eatingPlan = eatingPlan;
    }

    public DayInfoRequest(EatingPlansRequest eatingPlan) {
        this.eatingPlan = eatingPlan;
    }
}
