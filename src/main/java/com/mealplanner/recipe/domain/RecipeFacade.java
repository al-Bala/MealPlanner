package com.mealplanner.recipe.domain;

import com.mealplanner.home.HomeFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RecipeFacade {

    private final RecipeRepository repository;
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
        Recipe savedRecipe = repository.save(recipe);
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
