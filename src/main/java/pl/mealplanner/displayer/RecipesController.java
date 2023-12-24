package pl.mealplanner.displayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;
@Controller
public class RecipesController {

    private final RecipesRepository recipeRepository;
    private final MongoTemplate mongoTemplate;


    @Autowired
    public RecipesController(RecipesRepository recipeRepository, MongoTemplate mongoTemplate) {
        this.recipeRepository = recipeRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/przepisy")
    public String showRecipes(Model model) {
        List<Recipes> allRecipes = recipeRepository.findAll();

        int totalRecipes = allRecipes.size();
        int limit = 4;
        Random random = new Random();

        int[] randomIndexes = random.ints(0, totalRecipes).distinct().limit(limit).toArray();

        List<Recipes> randomRecipes = allRecipes.stream()
                .filter(recipe -> containsIndex(randomIndexes, allRecipes.indexOf(recipe)))
                .toList();

        model.addAttribute("recipes", randomRecipes); // Przekazanie losowych rekordów do modelu

        return "displayer/display"; // Zwrócenie nazwy szablonu HTML
    }
    private boolean containsIndex(int[] array, int index) {
        for (int i : array) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }
}

