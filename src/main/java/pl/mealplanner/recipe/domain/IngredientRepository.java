package pl.mealplanner.recipe.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IngredientRepository extends MongoRepository<Ingredient, String> {
    Ingredient findByName(String name);
}
