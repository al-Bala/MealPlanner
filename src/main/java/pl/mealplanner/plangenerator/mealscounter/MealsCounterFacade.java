package pl.mealplanner.plangenerator.mealscounter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoRequest;

import java.util.List;

@AllArgsConstructor
@Component
public class MealsCounterFacade {

    private final MealsCounterService mealsCounterService;

    public List<OneMealInfo> countNumberOfMeals(WeekInfoRequest weekInfoRequest){
        return mealsCounterService.countNumberOfMeals(weekInfoRequest);
    }
}
