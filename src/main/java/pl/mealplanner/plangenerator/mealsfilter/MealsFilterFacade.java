package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;

import java.util.List;

@AllArgsConstructor
public class MealsFilterFacade {

    private final MealsFilterService mealsFilterService;

    public List<String> findMeals(InfoForMealsSearch infoForMealsSearch){
        return mealsFilterService.findMeals(infoForMealsSearch);
    }
}
