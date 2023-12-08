package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.UserPreferencesDto;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoDto;

@Component
@AllArgsConstructor
public class PlanGeneratorFacade {

    private final MealsCounter mealsCounter;

    public String generateMealPlanner(WeekInfoDto weekInfoDto){

        System.out.println(mealsCounter.countNumberOfMeals(weekInfoDto));

        return null;
    }


}
