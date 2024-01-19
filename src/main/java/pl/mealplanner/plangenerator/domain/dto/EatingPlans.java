package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

@Builder
public record EatingPlans(
        String id,
        int timeMin
) {
}
