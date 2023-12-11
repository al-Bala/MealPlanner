package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.List;

@AllArgsConstructor
public class MealsFilterFacade {

    private final MealsFilterService mealsFilterService;

    public List<FilteredRecipeDto> findMeals(InfoForMealsSearch infoForMealsSearch){
        return mealsFilterService.findMeals(infoForMealsSearch);
    }
}
