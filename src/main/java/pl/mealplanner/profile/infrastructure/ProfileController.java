package pl.mealplanner.profile.infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

class ProfileController {

    @GetMapping("/recipes")
    public String getAllRecipes() {

        return "recipe/recipe-list";
    }

    @GetMapping("/recipes/{id}")
    public String getRecipeById(@PathVariable Long id) {

        return "recipe/recipe-page";
    }

    @PostMapping("/recipes")
    public String addRecipe() {

        return "recipe/recipe-form";
    }
}
