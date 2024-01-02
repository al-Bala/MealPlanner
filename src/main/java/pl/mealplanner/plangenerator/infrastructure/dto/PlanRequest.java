package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Builder;
import pl.mealplanner.plangenerator.domain.dto.UserPreferencesRequest;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoRequest;

@Builder
public record PlanRequest(
        UserPreferencesRequest preferences,
        WeekInfoRequest weekInfo
) {
    public PlanRequest(){
        this(new UserPreferencesRequest(), new WeekInfoRequest());
    }
}
