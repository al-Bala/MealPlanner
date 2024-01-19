package pl.mealplanner.profile.domain.entity;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Document
public record RecipeInPlanHistory(
        @Field("_id") ObjectId id,
        @Field("name") String name,
        @Field("portions") int portions,
        @Field("prepare_time") int prepareTime,
        @Field("diet") String diet,
        @Field("ingredients") List<IngredientPlanHistory> ingredients,
        @Field("steps") List<String> steps
) {
}
