package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.util.List;

@Builder(toBuilder = true)
public record ConvertedRecipe(
        ObjectId id,
        String name,
        int portions,
        int prepare_time,
        String diet,
        List<IngredientConverted> ingredients,
        List<String> steps
) {
}
