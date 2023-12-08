package pl.mealplanner.recipe.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mealplanner.plangenerator.domain.dto.OneMeal;

import java.util.List;
import java.util.Optional;

@Repository
interface IngredientRepository extends MongoRepository<Ingredient, String> {
    Ingredient findByName(String name);
}
