package com.mealplanner.recipe.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RecipeRepository extends MongoRepository<Recipe, String> {
}
