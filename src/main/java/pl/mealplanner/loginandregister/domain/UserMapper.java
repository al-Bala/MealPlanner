package pl.mealplanner.loginandregister.domain;

import pl.mealplanner.loginandregister.domain.dto.UserDto;

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
}
