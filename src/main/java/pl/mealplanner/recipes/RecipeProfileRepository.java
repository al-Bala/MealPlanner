package pl.mealplanner.recipes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mealplanner.displayer.Recipes;

public interface RecipeProfileRepository extends MongoRepository<Recipes
        , String> {
    // Dodatkowe metody repozytorium, jeśli są potrzebne
}
