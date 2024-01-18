package pl.mealplanner.profile.domain.entity;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Builder
@Document
public record PlanHistory(
        @Field("day") LocalDate date,
        @Field("recipe") RecipeInPlanHistory recipeInPlanHistory
) {
}
