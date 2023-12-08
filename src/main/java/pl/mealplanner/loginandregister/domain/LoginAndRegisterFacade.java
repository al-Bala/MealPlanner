package pl.mealplanner.loginandregister.domain;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mealplanner.loginandregister.domain.dto.Role;
import pl.mealplanner.loginandregister.domain.dto.UserDto;

@Log4j2
@AllArgsConstructor
@Component
public class LoginAndRegisterFacade {

    private final LoginAndRegisterRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserDto findUserByEmail(String email){
        return repository.findByEmail(email)
                .map(UserMapper::mapFromUserToUserDto)
                .orElseThrow(() -> new BadCredentialsException("Email not found"));
    }

    public UserDto saveUser(UserDto userDto){
        User user = User.builder()
                .role(Role.USER)
                .email(userDto.email())
                .password(passwordEncoder.encode(userDto.password()))
                .build();
        User savedUser = repository.save(user);
        log.info("User id: " + savedUser.id() + " added to database");
        return UserMapper.mapFromUserToUserDto(savedUser);
    }
}
