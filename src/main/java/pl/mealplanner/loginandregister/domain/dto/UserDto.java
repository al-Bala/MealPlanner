package pl.mealplanner.loginandregister.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record UserDto(
        @NotEmpty
        String username,
        @Email
        @NotEmpty
        String email,
        @NotEmpty
        @Size(min=8, message = "Hasło musi mieć conajmniej 8 znaków")
        String password,
        Role role
//        UserPreferences preferences,
//        List<Long> userRecipes,
//        PlanHistory planHistory
) {
}
