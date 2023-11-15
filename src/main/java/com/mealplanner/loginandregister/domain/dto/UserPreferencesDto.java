package com.mealplanner.loginandregister.domain.dto;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
public record UserPreferencesDto(
        int numberOfPortions,
        String diet,
        List<String> dislikedProducts
) {
}
