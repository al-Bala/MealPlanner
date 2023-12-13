package pl.mealplanner.loginandregister.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserPlanHistory(
        List<PlanHistoryDto> planHistoryList
) {
}
