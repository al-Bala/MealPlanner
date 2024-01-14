package pl.mealplanner.plangenerator.productscounter.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public record PackingMeasures(
        @Field("amount") Float amount,
        @Field("unit") String unit
) {
}
