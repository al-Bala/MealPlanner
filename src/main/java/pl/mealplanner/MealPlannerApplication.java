package pl.mealplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableMongoRepositories
public class MealPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MealPlannerApplication.class, args);
    }

}