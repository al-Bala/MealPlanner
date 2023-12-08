package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.MealsInfoDto;
import pl.mealplanner.plangenerator.domain.dto.UserPreferencesDto;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoDto;

@Component
@AllArgsConstructor
public class PlanGeneratorFacade {

    private final MealsCounter mealsCounter;

    public MealsInfoDto generateMealPlanner(WeekInfoDto weekInfoDto){
        return mealsCounter.countNumberOfMeals(weekInfoDto);
    }


}
