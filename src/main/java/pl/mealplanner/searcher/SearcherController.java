package pl.mealplanner.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mealplanner.displayer.Recipes;

import java.util.List;

@Controller
public class SearcherController {
    @Autowired
    private SearcherService searcherService;

    @RequestMapping("/wyszukiwarka")
    public String showRecipes(Model model) {
        // Sprawdza, czy model zawiera atrybut 'recipes' przekazany przez RedirectAttributes
        if (!model.containsAttribute("recipes")) {
            // Jeśli nie, ładuje domyślne, losowe przepisy
            model.addAttribute("recipes", searcherService.getRandomRecipes());
        }
        return "browser/searcher";
    }

    @PostMapping("/wyszukiwarka/wegetarianskie")
    public String showVegetarianRecipes(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("recipes", searcherService.getRandomRecipesByDiet("wegetariańska"));
        return "redirect:/wyszukiwarka";
    }

    @PostMapping("/wyszukiwarka/miesne")
    public String showMeatRecipes(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("recipes", searcherService.getRandomRecipesByDiet("mięsna"));
        return "redirect:/wyszukiwarka";
    }
}










