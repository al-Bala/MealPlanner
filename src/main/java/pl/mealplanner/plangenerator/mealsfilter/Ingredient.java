package pl.mealplanner.plangenerator.mealsfilter;

import org.springframework.data.mongodb.core.mapping.Field;

record Ingredient(
        @Field("name") String name,
        @Field("amount") float amount,
        @Field("unit") String unit
) {
}
