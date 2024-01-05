package pl.mealplanner.plangenerator.mealsfilter;

import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.List;

interface MealsFilterRepository {
    List<Recipe> findMatchingRecipes(InfoForFiltering info);
}
