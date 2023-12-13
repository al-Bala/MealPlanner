package pl.mealplanner.plangenerator.mealsfilter.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public record Ingredient(
        @Field("name") String name,
        @Field("amount") float amount,
        @Field("unit") String unit
) {
}
