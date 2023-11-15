package com.mealplanner.loginandregister.domain;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Document("users")
record User(
        @Id ObjectId id,
        @Field("username") String username,
        @Field("password") String password,
        @Field("email") String email,
        @Field("user_preferences") UserPreferences preferences,
        @Field("user_recipes") List<Long> userRecipes,
        @Field("plan_history") PlanHistory planHistory
){
}
