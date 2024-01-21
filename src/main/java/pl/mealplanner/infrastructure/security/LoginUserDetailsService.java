package pl.mealplanner.infrastructure.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.mealplanner.loginandregister.domain.LoginAndRegisterFacade;
import pl.mealplanner.loginandregister.domain.dto.Role;
import pl.mealplanner.loginandregister.domain.dto.UserDto;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final LoginAndRegisterFacade loginAndRegisterFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        UserDto userDto = loginAndRegisterFacade.findByUsername(username);
        return getUser(userDto);
    }

    private org.springframework.security.core.userdetails.User getUser(UserDto userDto) {
        return new org.springframework.security.core.userdetails.User(
                userDto.username(),
                userDto.password(),
                mapRolesToAuthorities(userDto.role()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
