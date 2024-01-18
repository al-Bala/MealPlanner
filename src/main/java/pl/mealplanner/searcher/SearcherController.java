package pl.mealplanner.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mealplanner.displayer.Recipes;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearcherController {




    @Autowired
    private SearcherService searcherService;

    public SearcherController(SearcherFetcher searcherFetcher) {
        this.searcherFetcher = searcherFetcher;
    }

    private final SearcherFetcher searcherFetcher;

    @RequestMapping("/wyszukiwarka")
    public String showRecipes(Model model) {
        if (!model.containsAttribute("recipes")) {
            model.addAttribute("recipes", searcherService.getRandomRecipes());
        }
        model.addAttribute("diets", searcherService.getAvailableDiets()); // Lista dostępnych diet
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

    @PostMapping("/wyszukiwarka/wybrana-dieta")
    public String showSelectedDietRecipes(@RequestParam("diet") String diet, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("recipes", searcherService.getRandomRecipesByDiet(diet));
        return "redirect:/wyszukiwarka";
    }

    @RequestMapping(value="/recipeNamesAutocomplete")
    @ResponseBody
    public List<Map<String, String>> productNamesAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term) {
        List<Map<String, String>> suggestions = new ArrayList<>();
        List<Recipes> allRecipes = searcherFetcher.fetch(term);

        for (Recipes recipe : allRecipes) {
            Map<String, String> suggestion = new HashMap<>();
            suggestion.put("id", recipe.getId());
            suggestion.put("name", recipe.getName());
            suggestions.add(suggestion);
        }
        return suggestions;
    }


}










