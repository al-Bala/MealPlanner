package com.mealplanner.recipe.domain;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Document("recipes")
record Recipe(
        @Id Long id,
        @Field("name") String name,
        @Field("author") String author,
        @Field("public") boolean isPublic,
        @Field("portions") int portions,
        @Field("prepare_time") int prepareTime,
        @Field("max_storage_time") int maxStorageTime,
        @Field("diet") String diet,
        @Field("ingredients") List<Ingredients> ingredients,
        @Field("steps") List<String> steps,
        @Field("photo") String url
) {
}
