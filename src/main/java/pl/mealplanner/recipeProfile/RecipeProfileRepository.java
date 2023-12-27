package pl.mealplanner.recipeProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mealplanner.displayer.Recipes;

public interface RecipeProfileRepository extends MongoRepository<Recipes
        , String> {
    // Dodatkowe metody repozytorium, jeśli są potrzebne
}
