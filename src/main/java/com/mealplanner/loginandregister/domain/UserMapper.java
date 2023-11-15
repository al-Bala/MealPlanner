package com.mealplanner.loginandregister.domain;

import com.mealplanner.loginandregister.domain.dto.UserDto;

class UserMapper {
    public static User mapFromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.username())
                .password(userDto.password())
                .email(userDto.email())

                .build();
    }

    public static UserDto mapFromUserToUserDto(User user) {
        return UserDto.builder()
                .username(user.username())
                .password(user.password())
                .email(user.email())

                .build();
    }
}
