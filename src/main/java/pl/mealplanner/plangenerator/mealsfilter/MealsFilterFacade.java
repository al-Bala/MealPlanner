package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.loginandregister.domain.LoginAndRegisterFacade;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.List;

@AllArgsConstructor
@Component
public class MealsFilterFacade {

    private final MealsFilterService mealsFilterService;

    public List<FilteredRecipeDto> findMeals(InfoForMealsSearch infoForMealsSearch){
        return mealsFilterService.findMeals(infoForMealsSearch);
    }
}
