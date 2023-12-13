package pl.mealplanner.loginandregister.domain.entity;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Document
public record UserPreferences(
        @Field("portions") int numberOfPortions,
        @Field("diet") String diet,
        @Field("disliked_products") List<String> dislikedProducts
) {
}
