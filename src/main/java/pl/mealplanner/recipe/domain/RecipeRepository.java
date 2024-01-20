package pl.mealplanner.recipe.domain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.mealplanner.recipe.domain.entity.RecipeClass;

import java.util.List;

public interface RecipeRepository extends MongoRepository<RecipeClass, String> {
    // Dodatkowe metody repozytorium, jeśli są potrzebne

    List<RecipeClass> findByDiet(String diet);

    @Query(value = "{}", fields = "{diet : 1}")
    List<RecipeClass> findAllDiets();
}
