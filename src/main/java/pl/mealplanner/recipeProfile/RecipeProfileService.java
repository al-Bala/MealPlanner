package pl.mealplanner.recipeProfile;

import org.springframework.stereotype.Service;
import pl.mealplanner.displayer.Recipes;

import java.util.Optional;

@Service
public class RecipeProfileService {

    private final RecipeProfileRepository recipeProfileRepository;

    public RecipeProfileService(RecipeProfileRepository recipeProfileRepository) {
        this.recipeProfileRepository = recipeProfileRepository;
    }

    public Optional<Recipes> findRecipeById(String id) {
        return recipeProfileRepository.findById(id);
    }
}
