package pl.mealplanner.plangenerator.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record InfoForMealsSearch(
        List<OneMealInfo> oneMealInfoList,
        UserPreferences preferencesDto
) {
}
