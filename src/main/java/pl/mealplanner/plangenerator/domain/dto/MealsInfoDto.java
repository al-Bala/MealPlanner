package pl.mealplanner.plangenerator.domain.dto;

import java.util.List;

public record MealsInfoDto(
        List<OneMealInfo> oneMealInfoList
) {
}
