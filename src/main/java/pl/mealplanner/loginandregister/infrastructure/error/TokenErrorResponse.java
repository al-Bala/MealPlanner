package pl.mealplanner.loginandregister.infrastructure.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(
        String message,
        HttpStatus status
) {
}
