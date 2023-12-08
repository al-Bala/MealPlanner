package pl.mealplanner.recipe.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

record Product(
        @Field("name") String name,
        @Field("packing_measures") List<PackingMeasures> packingMeasures
) {
}
