package pl.mealplanner.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mealplanner.displayer.Recipes;

import java.util.List;

@Controller
public class SearcherController {
    @Autowired
    private SearcherService service;

    @GetMapping("/wyszukiwarka/sugestie")
    @ResponseBody
    public List<Recipes> getSuggestions(@RequestParam String keyword) {
        return service.getSuggestions(keyword);
    }

    @GetMapping("/wyszukiwarka")
    public String searchRecipes(@RequestParam(required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("recipes", service.getSuggestions(keyword));
        } else {
            model.addAttribute("recipes", service.nullowanie());
        }

        return "browser/searcher";

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










