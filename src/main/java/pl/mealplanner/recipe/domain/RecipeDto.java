package pl.mealplanner.recipe.domain;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
public record RecipeDto(
        ObjectId id,
        String name,
        int portions,
        int prepareTime,
        String diet,
        List<Ingredient> ingredients,
        List<String> steps
) {
}
