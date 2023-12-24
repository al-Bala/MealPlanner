package pl.mealplanner.browser;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mealplanner.displayer.Recipes;

import java.util.List;


@Controller
public class BrowserController {

    private BrowserRepository browserRepository;

    public BrowserController(BrowserRepository browserRepository) {
        this.browserRepository = browserRepository;
    }

    @GetMapping("/przepisy-miesne")
        public String showMeatRecipesByDiet (Model model){

            List<Recipes> meatRecipes = browserRepository.findByDiet("mięsna");
            model.addAttribute("meatRecipes", meatRecipes); // Przekazanie mięsnych przepisów do modelu
            return "browser/meatRecipes"; // Zwraca nazwę widoku HTML
        }

    @GetMapping("/przepisy-wegetarianskie")
    public String showVeganRecipesByDiet (Model model){

        List<Recipes> vegetarianRecipes = browserRepository.findByDiet("wegetariańska");
        model.addAttribute("vegetarianRecipes", vegetarianRecipes); // Przekazanie wegetariańskich przepisów do modelu
        return "browser/veganRecipes"; // Zwraca nazwę widoku HTML
    }
}



