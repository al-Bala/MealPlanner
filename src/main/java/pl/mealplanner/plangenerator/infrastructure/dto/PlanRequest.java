package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Data;

@Data
public class PlanRequest {
    UserPreferencesRequest preferences;
    WeekInfoRequest weekInfo;

    public PlanRequest(){

    }

    public PlanRequest(UserPreferencesRequest preferences, WeekInfoRequest weekInfo) {
        this.preferences = preferences;
        this.weekInfo = weekInfo;
    }
}
