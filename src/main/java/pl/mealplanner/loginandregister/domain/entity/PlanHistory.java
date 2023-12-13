package pl.mealplanner.loginandregister.domain.entity;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Builder
@Document
public record PlanHistory(
        @Field("day") LocalDate date,
        @Field("recipe") ObjectId recipe
) {
}
