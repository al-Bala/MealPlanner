package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.dto.MatchingRecipe;

@AllArgsConstructor
@Component
public class MealsFilterFacade {
    private final MealsFinder mealsFinder;

    public MatchingRecipe findRecipe(InfoForFiltering info) {
        return mealsFinder.findMatchingRecipe(info, 1);
    }
}
