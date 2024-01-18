package pl.mealplanner.profile.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mealplanner.displayer.Recipes;
import pl.mealplanner.loginandregister.domain.entity.User;
import pl.mealplanner.profile.infrastructure.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserFetcher userFetcher; // Wstrzyknięcie zależności

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public String showUserDetails(@PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        if (user != null) {
            model.addAttribute("user", user.username());
            model.addAttribute("id", user.id());
            model.addAttribute("role", user.role());
            model.addAttribute("favorites", userService.getFavoriteRecipes(username));
            return "details/user-details"; // nazwa widoku (user-details.html)
        } else {
            return "details/user-details"; // widok informujący o braku użytkownika
        }
    }

    @RequestMapping(value="/favoriteRecipesAutocomplete")
    @ResponseBody
    public List<Map<String, String>> favoriteRecipesAutocomplete(@RequestParam(value="userId") String userId, @RequestParam(value="term", required = false, defaultValue="") String term) {
        List<Map<String, String>> suggestions = new ArrayList<>();
        List<Recipes> favoriteRecipes = userFetcher.fetchFavorites(userId, term);
        for (Recipes recipe : favoriteRecipes) {
            Map<String, String> suggestion = new HashMap<>();
            suggestion.put("id", recipe.getId());
            suggestion.put("name", recipe.getName());
            suggestions.add(suggestion);
        }
        return suggestions;
    }


}