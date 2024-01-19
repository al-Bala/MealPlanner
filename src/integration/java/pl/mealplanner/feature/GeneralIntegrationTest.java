package pl.mealplanner.feature;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import pl.mealplanner.BaseIntegrationTest;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;
import pl.mealplanner.plangenerator.infrastructure.dto.*;
import pl.mealplanner.plangenerator.plan.dto.DisplayPlan;
import pl.mealplanner.plangenerator.productscounter.dto.GroceryList;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class GeneralIntegrationTest extends BaseIntegrationTest {
    @Autowired
    public PlanGeneratorFacade planGeneratorFacade;
    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    public void general_test(){
        // given
        UserPreferencesRequest preferencesRequest = getPreferences();
        WeekInfoRequest weekInfoRequest = getWeekInfo();

        // when
        List<DisplayPlan> foundMeals = planGeneratorFacade.generateMealPlanner(preferencesRequest, weekInfoRequest);
        List<GroceryList> groceryList = planGeneratorFacade.getGroceryListForPlan();
        List<DisplayPlan> currentPlan = planGeneratorFacade.getCurrentPlan();

        // then
        System.out.println(foundMeals);
        System.out.println(groceryList);
        System.out.println(currentPlan);


    }

    @NotNull
    private static UserPreferencesRequest getPreferences() {
        return new UserPreferencesRequest(
                2,
                "brakDiety",
                List.of(
//                        new IngredientRequest("marchew", 100, "g")
//                        new IngredientRequest("marchew", 2, "szt")
                        new IngredientRequest("marchew", 100, "g")
                ),
                Collections.emptyList()
        );
    }

    @NotNull
    private static WeekInfoRequest getWeekInfo() {
        return new WeekInfoRequest(
                List.of(
                        new DayInfoRequest(
                                LocalDate.of(2024, 1,15),
                                new EatingPlansRequest("C01", -1)),
                        new DayInfoRequest(
                                LocalDate.of(2024, 1,16),
                                new EatingPlansRequest("E03", -1)),
                        new DayInfoRequest(
                                LocalDate.of(2024, 1,17),
                                new EatingPlansRequest("C01", -1))
                )
        );
    }
}
