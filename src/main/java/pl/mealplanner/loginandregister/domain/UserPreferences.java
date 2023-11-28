package pl.mealplanner.loginandregister.domain;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Document
record UserPreferences(
        @Field("portions") int numberOfPortions,
        @Field("diet") String diet,
        @Field("disliked_products") List<String> dislikedProducts
) {
}
