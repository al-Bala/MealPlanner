package pl.mealplanner.plangenerator.mealsfilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MealsFilterConfig {
    @Bean
    MealFilterRepository mealFilterRepository(){
        return new MealFilterRepositoryImpl(new MealsFilterDatabaseConfig());
    }

    @Bean
    MealsFilterFacade mealsFilterFacade(){
        MealsFilterService service = new MealsFilterService(new MealFilterRepositoryImpl(new MealsFilterDatabaseConfig()));
        return new MealsFilterFacade(service);
    }
    public MealsFilterFacade createForTest(){
        return new MealsFilterFacade(new MealsFilterService(new MealFilterRepositoryImpl(new MealsFilterDatabaseConfig())));
    }
}
