package pl.mealplanner.loginandregister.domain;

import pl.mealplanner.loginandregister.domain.dto.UserDto;
import pl.mealplanner.profile.domain.User;

class LoginAndRegisterMapper {
    public static User mapFromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.username())
                .email(userDto.email())
                .password(userDto.password())
                .build();
    }

    public static UserDto mapFromUserToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
