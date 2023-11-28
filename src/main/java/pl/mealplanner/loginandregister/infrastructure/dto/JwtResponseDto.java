package pl.mealplanner.loginandregister.infrastructure.dto;

import lombok.Builder;

@Builder
public record JwtResponseDto(
        String username,
        String token
) {
}
