package pl.mealplanner.displayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecipesService {
    @Autowired
    private RecipesRepository recipesRepository;

    public List<Recipes> getRandomRecipes() {
        List<Recipes> allRecipes = recipesRepository.findAll();
        Collections.shuffle(allRecipes);
        return allRecipes.subList(0, Math.min(3, allRecipes.size()));
    }

    public List<Recipes> getRandomRecipesByDiet(String diet) {
        List<Recipes> dietRecipes = recipesRepository.findByDiet(diet);
        Collections.shuffle(dietRecipes);
        return dietRecipes.subList(0, Math.min(3, dietRecipes.size()));
    }
}