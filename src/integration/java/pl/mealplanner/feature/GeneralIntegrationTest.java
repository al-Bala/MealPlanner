package pl.mealplanner.feature;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import pl.mealplanner.BaseIntegrationTest;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;
import pl.mealplanner.plangenerator.infrastructure.dto.*;
import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        List<ConvertedRecipe> foundMeals = planGeneratorFacade.generateMealPlanner(preferencesRequest, weekInfoRequest);
        Set<PlanProductInfo> groceryList = planGeneratorFacade.getGroceryListForPlan();

        // then
        System.out.println(foundMeals);
        System.out.println(groceryList);


    }

    @NotNull
    private static UserPreferencesRequest getPreferences() {
        return new UserPreferencesRequest(
                2,
                "wegetariańska",
                List.of(
//                        new IngredientRequest("marchew", 100, "g")
//                        new IngredientRequest("marchew", 2, "szt")
                        new IngredientRequest("brokuł", 1, "szt")
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
                                new EatingPlansRequest("C01", -1))
                )
        );
    }
}
