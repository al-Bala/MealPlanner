package pl.mealplanner.loginandregister.infrastructure.dto;

public record TokenRequest(
        String username,
        String password
){
}
