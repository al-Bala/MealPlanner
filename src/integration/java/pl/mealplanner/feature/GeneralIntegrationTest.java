package pl.mealplanner.feature;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import pl.mealplanner.BaseIntegrationTest;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;
import pl.mealplanner.plangenerator.infrastructure.dto.*;
import pl.mealplanner.plangenerator.plan.dto.DisplayPlan;
import pl.mealplanner.plangenerator.plan.dto.FirstDisplayRecipe;
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

        // then
        List<DisplayPlan> expectedFoundMeals = List.of(
                new DisplayPlan("Poniedziałek", "15-01-2024",
                        new FirstDisplayRecipe("6577660abbac733a111c9421", "Kasza jaglana z warzywami", 30)),
                new DisplayPlan("Wtorek", "16-01-2024",
                        new FirstDisplayRecipe("EMPTY_DAY_ID", null, 0)),
                new DisplayPlan("Środa", "17-01-2024",
                        new FirstDisplayRecipe("6577660abbac733a111c9425", "Ryż z warzywami i kurczakiem", 25))
                );

        List<GroceryList> expectedGroceryList = List.of(
                new GroceryList("oliwa z oliwek", 30f, 50f ,1, "ml"),
                new GroceryList("kurczak", 166f, 400f ,1, "g"),
                new GroceryList("brokuł", 250f, 150f ,2, "g"),
                new GroceryList("ryż", 200f, 400f ,1, "g"),
                new GroceryList("marchew", 333f, 100f ,4, "g"),
                new GroceryList("sos sojowy", 20f, 150f ,1, "ml")
        );

        Assertions.assertEquals(expectedFoundMeals, foundMeals);
        Assertions.assertEquals(expectedGroceryList, groceryList);

    }

    @NotNull
    private static UserPreferencesRequest getPreferences() {
        return new UserPreferencesRequest(
                2,
                "brakDiety",
                List.of(
                        new IngredientRequest("marchew", 1, "szt"),
                        new IngredientRequest("kasza jaglana", 200, "g")
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
