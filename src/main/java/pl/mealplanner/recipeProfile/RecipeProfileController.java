package pl.mealplanner.recipeProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mealplanner.displayer.Recipes;
import pl.mealplanner.profile.infrastructure.UserService;

import java.util.Optional;

@Controller
public class RecipeProfileController {

    private final String bucketName = "mealplannerup.s3.";
    private final String region = "eu-north-1";
    private final RecipeProfileService recipeProfileService;

    @Autowired
    private UserService userService;


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
            model.addAttribute("id", recipe.getId());
            return "details/recipe-details"; // Nazwa pliku HTML z szablonem dla szczegółów przepisu
        } else {
            return "details/recipe-details"; // Widok HTML informujący o braku przepisu
        }
    }

    @PostMapping("/recipes/{id}/favorite")
    public String toggleRecipeFavorite(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String message = userService.toggleFavorite(username, id);

        redirectAttributes.addFlashAttribute("favoriteMessage", message);
        return "redirect:/recipes/" + id;
    }


}
