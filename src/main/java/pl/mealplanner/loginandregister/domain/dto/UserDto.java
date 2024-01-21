package pl.mealplanner.loginandregister.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import pl.mealplanner.loginandregister.domain.Password;

@Builder
public record UserDto(
        @NotEmpty
        String username,
        @Email
        @NotEmpty
        String email,
        @Password
        String password,
        Role role
) {
}
