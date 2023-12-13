package pl.mealplanner.loginandregister.domain;

import pl.mealplanner.loginandregister.domain.dto.PlanHistoryDto;
import pl.mealplanner.loginandregister.domain.dto.UserDto;
import pl.mealplanner.loginandregister.domain.dto.UserPlanHistory;
import pl.mealplanner.loginandregister.domain.entity.User;

import java.util.List;

class UserMapper {
    public static User mapFromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .email(userDto.email())
                .password(userDto.password())
                .build();
    }

    public static UserDto mapFromUserToUserDto(User user) {
        return UserDto.builder()
                .email(user.email())
                .password(user.password())
                .role(user.role())
                .build();
    }

    public static UserPlanHistory mapFromUserToUserPlanHistory(User user) {
        List<PlanHistoryDto> planHistoryDto = user.planHistory().stream()
                .map(p -> new PlanHistoryDto(p.date(), p.recipe()))
                .toList();
        return new UserPlanHistory(planHistoryDto);
    }
}
