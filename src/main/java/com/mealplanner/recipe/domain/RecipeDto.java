package com.mealplanner.recipe.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record RecipeDto(
        String name,
        boolean isPublic,
        int portions,
        int prepareTime,
        int maxStorageTime,
        String diet,
        List<Ingredients> ingredients,
        List<String> steps
) {
}
