package pl.mealplanner.loginandregister.domain.dto;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Builder
public record PlanHistoryDto(
        LocalDate day,
        ObjectId recipeId
) {
}
