package pl.mealplanner.recipeProfile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mealplanner.displayer.Recipes;

import java.util.Optional;

@Controller
public class RecipeProfileController {

    private final String bucketName = "mealplannerup.s3.";
    private final String region = "eu-north-1";
    private final RecipeProfileService recipeProfileService;

    public RecipeProfileController(RecipeProfileService recipeProfileService) {
        this.recipeProfileService = recipeProfileService;
    }

    @GetMapping("/recipes/{id}")
    public String showRecipeDetails(@PathVariable String id, Model model) {
        Optional<Recipes> recipeOptional = recipeProfileService.findRecipeById(id);
        String imageUrl = "https://" + bucketName + region + ".amazonaws.com/" + id +".jpg";


        if (recipeOptional.isPresent()) {
            Recipes recipe = recipeOptional.get();
            model.addAttribute("diet", recipe.getDiet());
            model.addAttribute("prepareTime", recipe.getPrepareTime());
            model.addAttribute("name", recipe.getName());
            model.addAttribute("image", recipe.getImage());
            model.addAttribute("imageUrl", imageUrl);
            return "details/recipe-details"; // Nazwa pliku HTML z szablonem dla szczegółów przepisu
        } else {
            return "details/recipe-details"; // Widok HTML informujący o braku przepisu
        }
    }
}
