package pl.mealplanner.recipe.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mealplanner.recipe.domain.entity.RecipeClass;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;


    public List<RecipeClass> getRandomRecipes() {
        List<RecipeClass> allRecipes = recipeRepository.findAll();
        Collections.shuffle(allRecipes);
        return allRecipes.subList(0, Math.min(3, allRecipes.size()));
    }

    public List<RecipeClass> getRandomRecipesByDiet(String diet) {
        List<RecipeClass> dietRecipes = recipeRepository.findByDiet(diet);
        Collections.shuffle(dietRecipes);
        return dietRecipes.subList(0, Math.min(3, dietRecipes.size()));
    }

    public List<String> getAvailableDiets() {
        List<RecipeClass> recipeClassWithDiet = recipeRepository.findAllDiets();
        return recipeClassWithDiet.stream()
                .map(RecipeClass::getDiet)
                .distinct()
                .collect(Collectors.toList());
    }

    public Optional<RecipeClass> findRecipeById(String id) {
        return recipeRepository.findById(id);
    }
}
