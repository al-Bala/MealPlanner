package com.mealplanner.loginandregister.domain;

import com.mealplanner.loginandregister.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@AllArgsConstructor
@Component
public class LoginAndRegisterFacade {

    private final LoginAndRegisterRepository repository;

    public String findByUsername(){
        return null;
    }

    public UserDto register(UserDto userDto){
        User user = UserMapper.mapFromUserDtoToUser(userDto);
        User savedUser = repository.save(user);
        log.info("User id: " + savedUser.id() + " added to database");
        return UserMapper.mapFromUserToUserDto(savedUser);
    }

}
