package pl.mealplanner.profile.domain;

import pl.mealplanner.loginandregister.domain.dto.PlanHistoryDto;

import java.util.List;

class UserMapper {

    public static List<PlanHistoryDto> mapFromUserToPlanHistoryList(User user) {
        return user.getPlanHistory().stream()
                .map(p -> new PlanHistoryDto(p.date(), p.recipeId()))
                .toList();
    }
}
