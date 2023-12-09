package pl.mealplanner.plangenerator.mealscounter;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MealsCounterConfig {
    public MealsCounterFacade createForTest(){
        return new MealsCounterFacade(new MealsCounterService());
    }
}
