package pl.mealplanner.recipe.domain;

import org.springframework.data.mongodb.core.mapping.Field;

record PackingMeasures(
        @Field("amount") float amount,
        @Field("unit") String unit
) {
}
