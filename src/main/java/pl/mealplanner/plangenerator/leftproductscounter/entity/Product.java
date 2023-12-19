package pl.mealplanner.plangenerator.leftproductscounter.entity;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Document("products")
public record Product(
        @Field("name") String name,
        @Field("packing_measures") List<PackingMeasures> packingMeasures
) {
}
