package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import pl.mealplanner.plangenerator.mealscounter.MealsCounterConfig;
import pl.mealplanner.plangenerator.mealsfilter.MealsFilterConfig;

@AllArgsConstructor
@Configuration
public class PlanGeneratorConfig {

    private final MealsCounterConfig mealsCounterConfig;
    private final MealsFilterConfig mealsFilterConfig;
    public PlanGeneratorFacade createForTest() {
        return new PlanGeneratorFacade(mealsCounterConfig.createForTest(), mealsFilterConfig.createForTest());
    }
}
