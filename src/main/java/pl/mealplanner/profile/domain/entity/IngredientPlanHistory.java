package pl.mealplanner.profile.domain.entity;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Document
public record IngredientPlanHistory(
        @Field("name") String name,
        @Field("amount") float amount,
        @Field("unit") String unit
) {
}
