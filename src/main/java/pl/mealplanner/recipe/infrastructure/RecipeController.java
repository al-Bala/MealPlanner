package pl.mealplanner.recipe.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mealplanner.profile.domain.UserService;
import pl.mealplanner.recipe.domain.RecipeService;
import pl.mealplanner.recipe.domain.RecipeFetcher;
import pl.mealplanner.recipe.domain.entity.RecipeClass;

import java.util.*;

@AllArgsConstructor
@Controller
public class RecipeController {

    private final String bucketName = "mealplannerup.s3.";
    private final String region = "eu-north-1";
    private final RecipeService recipeService;

    private UserService userService;
    private final RecipeFetcher recipeFetcher;

    @GetMapping("/recipes/{id}")
    public String showRecipeDetails(@PathVariable String id, Model model) {
        Optional<RecipeClass> recipeOptional = recipeService.findRecipeById(id);
        String imageUrl = "https://" + bucketName + region + ".amazonaws.com/" + id +".jpg";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        boolean isInFavorite = userService.isFavorite(username, id);
        model.addAttribute("isInFavorite", isInFavorite);

        if (recipeOptional.isPresent()) {
            RecipeClass recipe = recipeOptional.get();
            model.addAttribute("diet", recipe.getDiet());
            model.addAttribute("prepareTime", recipe.getPrepare_time());
            model.addAttribute("name", recipe.getName());
            model.addAttribute("ingredients", recipe.getIngredients());
            model.addAttribute("steps", recipe.getSteps());
            model.addAttribute("image", recipe.getImage());
            model.addAttribute("imageUrl", imageUrl);
            model.addAttribute("isInFavorite", isInFavorite);
            model.addAttribute("id", recipe.getId());
            return "details/recipe-details"; // strona z przepisem
        } else {
            return "browser/searcher"; //
        }
    }

    @PostMapping("/recipes/{id}/favorite")
    public String toggleRecipeFavorite(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();


        userService.toggleFavorite(username, id);
        return "redirect:/recipes/" + id;
    }

    @RequestMapping("/recipes")
    public String showRecipes(Model model) {


        if (!model.containsAttribute("recipes")) {
            List<RecipeClass> randomRecipes = recipeService.getRandomRecipes();
            Map<String, String> imageUrlMap = new HashMap<>();

            randomRecipes.forEach(recipe -> {
                String imageUrl = "https://" + bucketName + region + ".amazonaws.com/" + recipe.getId() + ".jpg";
                imageUrlMap.put(recipe.getId(), imageUrl);
            });

            model.addAttribute("recipes", randomRecipes);
            model.addAttribute("imageUrlMap", imageUrlMap);
        }
        model.addAttribute("diets", recipeService.getAvailableDiets()); // Lista dostępnych diet
        return "/browser/searcher";
    }

    @PostMapping("/recipes/diet")
    public String showSelectedDietRecipes(@RequestParam("diet") String diet, RedirectAttributes redirectAttributes) {
        List<RecipeClass> randomRecipeClassByDiet = recipeService.getRandomRecipesByDiet(diet);
        Map<String, String> imageUrlMap = new HashMap<>();

        randomRecipeClassByDiet.forEach(recipe -> {
            String imageUrl = "https://" + bucketName + region + ".amazonaws.com/" + recipe.getId() + ".jpg";
            imageUrlMap.put(recipe.getId(), imageUrl);
        });

        redirectAttributes.addFlashAttribute("imageUrlMap", imageUrlMap);
        redirectAttributes.addFlashAttribute("recipes", randomRecipeClassByDiet);
        return "redirect:/recipes";
    }

    @RequestMapping(value="/recipeNamesAutocomplete")
    @ResponseBody
    public List<Map<String, String>> productNamesAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term) {
        List<Map<String, String>> suggestions = new ArrayList<>();
        List<RecipeClass> allRecipes = recipeFetcher.fetch(term);

        for (RecipeClass recipe : allRecipes) {
            Map<String, String> suggestion = new HashMap<>();
            suggestion.put("id", recipe.getId());
            suggestion.put("name", recipe.getName());
            suggestions.add(suggestion);
        }
        return suggestions;
    }




}
