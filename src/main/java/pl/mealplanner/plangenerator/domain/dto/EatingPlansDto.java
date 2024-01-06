package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

@Builder
public record EatingPlansDto(
        String id,
        int timeMin
) {
}
