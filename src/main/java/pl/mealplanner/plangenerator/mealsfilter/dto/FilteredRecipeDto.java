package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
public record FilteredRecipeDto(
        ObjectId id,
        String name,
        int portions,
        int prepare_time,
        int max_storage_time,
        String diet,
        List<ProductDto> ingredients
) {
}
