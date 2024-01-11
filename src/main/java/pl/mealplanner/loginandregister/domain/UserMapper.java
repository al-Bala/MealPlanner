package pl.mealplanner.loginandregister.domain;

import pl.mealplanner.loginandregister.domain.dto.PlanHistoryDto;
import pl.mealplanner.loginandregister.domain.dto.UserDto;
import pl.mealplanner.loginandregister.domain.entity.User;

import java.util.List;

class UserMapper {
    public static User mapFromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.username())
                .email(userDto.email())
                .password(userDto.password())
                .build();
    }

    public static UserDto mapFromUserToUserDto(User user) {
        return UserDto.builder()
                .username(user.username())
                .email(user.email())
                .password(user.password())
                .role(user.role())
                .build();
    }

    public static List<PlanHistoryDto> mapFromUserToPlanHistoryList(User user) {
        return user.planHistory().stream()
                .map(p -> new PlanHistoryDto(p.date(), p.recipe()))
                .toList();
    }
}
