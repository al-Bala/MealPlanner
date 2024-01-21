package pl.mealplanner.loginandregister.infrastructure.error;

import org.springframework.http.HttpStatus;

public record LoginErrorResponse(
        String message,
        HttpStatus status
) {
}
