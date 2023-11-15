package com.mealplanner.loginandregister.domain;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Builder
@Document
record PlanHistory(
        @Field("day") LocalDate date,
        @Field("recipe") Long recipe
) {
}
