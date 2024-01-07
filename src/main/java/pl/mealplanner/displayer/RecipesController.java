package pl.mealplanner.displayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.util.List;

@Controller
public class RecipesController {

    @Autowired
    private RecipesService recipesService;

    // Wstrzyknięcie zależności przez konstruktor
    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @RequestMapping("/recipes")
    public String showRecipes(Model model) {
        // Sprawdza, czy model zawiera atrybut 'recipes' przekazany przez RedirectAttributes
        if (!model.containsAttribute("recipes")) {
            // Jeśli nie, ładuje domyślne, losowe przepisy
            model.addAttribute("recipes", recipesService.getRandomRecipes());
        }
        return "displayer/display";
    }

    @PostMapping("/recipes/wegetarianskie")
    public String showVegetarianRecipes(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("recipes", recipesService.getRandomRecipesByDiet("wegetariańska"));
        return "redirect:/recipes";
    }

    @PostMapping("/recipes/miesne")
    public String showMeatRecipes(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("recipes", recipesService.getRandomRecipesByDiet("mięsna"));
        return "redirect:/recipes";
    }
}
