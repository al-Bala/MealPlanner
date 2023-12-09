package pl.mealplanner.recipe.domain;

import pl.mealplanner.home.HomeFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RecipeFacade {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final HomeFacade homeFacade;

    public String findAllRecipes(){
        return null;
    }

    public String findRecipeById(){
        return null;
    }

    public RecipeDto addRecipe(RecipeDto recipeDto){
        homeFacade.checkUser();

        Recipe recipe = RecipeMapper.mapFromRecipeDtoToRecipe(recipeDto);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeMapper.mapFromRecipeToRecipeDto(savedRecipe);
    }

    public String editRecipe(){
        return null;
    }

    public String deleteRecipe(){
        return null;
    }

    public String addToFavourites(){
        return null;
    }
}
