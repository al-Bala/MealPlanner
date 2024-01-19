package pl.mealplanner.plangenerator.mealscounter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.WeekInfo;

import java.util.List;

@AllArgsConstructor
@Component
public class MealsCounterFacade {

    private final MealsCounterService mealsCounterService;

    public List<OneMealInfo> countNumberOfMeals(WeekInfo weekInfo){
        return mealsCounterService.countNumberOfMeals(weekInfo);
    }
}
