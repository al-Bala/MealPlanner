package com.mealplanner.recipe.domain.model;

import org.springframework.data.mongodb.core.mapping.Field;

record Ingredients(
        @Field("name") String name,
        @Field("amount") float amount,
        @Field("unit") String unit
) {
}
