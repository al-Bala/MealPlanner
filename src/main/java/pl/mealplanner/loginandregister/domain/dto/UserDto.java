package pl.mealplanner.loginandregister.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserDto(
        //String username,
        String password,
        String email,
        Role role
//        UserPreferences preferences,
//        List<Long> userRecipes,
//        PlanHistory planHistory
) {
}
