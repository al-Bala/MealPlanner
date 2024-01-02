package pl.mealplanner.loginandregister.domain;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mealplanner.loginandregister.domain.dto.Role;
import pl.mealplanner.loginandregister.domain.dto.UserDto;
import pl.mealplanner.loginandregister.domain.dto.UserPlanHistory;
import pl.mealplanner.loginandregister.domain.entity.User;

@Log4j2
@AllArgsConstructor
@Component
public class LoginAndRegisterFacade {

    private final LoginAndRegisterRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserDto findByUsername(String username) {
        return repository.findByUsername(username)
                .map(UserMapper::mapFromUserToUserDto)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public boolean isUsernameExists(String username) {
        return repository.existsByUsername(username);
    }

    public boolean isEmailExists(String email) {
        return repository.existsByEmail(email);
    }

    public UserPlanHistory findPlanHistoryByUsername(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return UserMapper.mapFromUserToUserPlanHistory(user);
    }


    public UserDto saveUser(UserDto userDto) {
        User user = User.builder()
                .role(Role.USER)
                .username(userDto.username())
                .email(userDto.email())
                .password(passwordEncoder.encode(userDto.password()))
                .build();
        User savedUser = repository.save(user);
        log.info("User id: " + savedUser.id() + " added to database");
        return UserMapper.mapFromUserToUserDto(savedUser);
    }
}
