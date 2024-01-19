package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.*;
import pl.mealplanner.plangenerator.infrastructure.dto.DayInfoRequest;
import pl.mealplanner.plangenerator.infrastructure.dto.IngredientRequest;
import pl.mealplanner.plangenerator.infrastructure.dto.UserPreferencesRequest;
import pl.mealplanner.plangenerator.infrastructure.dto.WeekInfoRequest;
import pl.mealplanner.plangenerator.mealscounter.MealsCounterFacade;
import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;
import pl.mealplanner.plangenerator.mealsfilter.dto.MealPlanElement;
import pl.mealplanner.plangenerator.plan.PlanFacade;
import pl.mealplanner.plangenerator.plan.dto.DisplayPlan;
import pl.mealplanner.plangenerator.productscounter.ListOfProductsForPlan;
import pl.mealplanner.plangenerator.productscounter.dto.GroceryList;
import pl.mealplanner.profile.domain.entity.PlanHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Component
public class PlanGeneratorFacade {

    private final MealsCounterFacade mealsCounterFacade;
    private final PlanGeneratorService planGeneratorService;
    private final ListOfProductsForPlan listOfProductsForPlan;
    private final PlanFacade planFacade;
    public static List<ConvertedRecipe> allRecipesForPlan = new ArrayList<>();

    public List<DisplayPlan> generateMealPlanner(UserPreferencesRequest preferencesRequest, WeekInfoRequest weekInfoRequest){
        listOfProductsForPlan.clearListOfProductsForPlan();
        setDates(weekInfoRequest);
        UserPreferences preferences = PlanGeneratorMapper.mapFromUserPreferencesRequestToUserPreferencesDto(preferencesRequest);
        WeekInfo weekInfo = PlanGeneratorMapper.mapFromWeekInfoRequestToWeekInfoDto(weekInfoRequest);

        List<OneMealInfo> oneMealInfoList = mealsCounterFacade.countNumberOfMeals(weekInfo);
        InfoForMealsSearch infoForMealsSearch = InfoForMealsSearch.builder()
                .oneMealInfoList(oneMealInfoList)
                .preferencesDto(preferences)
                .build();

        List<MealPlanElement> mealPlan = planGeneratorService.generatePlan(infoForMealsSearch);
        planFacade.savePlanAndGroceryList(mealPlan, listOfProductsForPlan.mapToGroceryList());

        return DisplayMapper.mapFromMealPlanElementToDisplay(mealPlan);
    }

    private static void setDates(WeekInfoRequest weekInfoRequest) {
        LocalDate date = weekInfoRequest.getDayInfoList().get(0).getDay();
        List<DayInfoRequest> list = weekInfoRequest.getDayInfoList();
        int i = 0;
        for (DayInfoRequest day : list) {
            day.setDay(date.plusDays(i));
            i++;
        }
    }

    public List<DisplayPlan> getCurrentPlan() {
        List<PlanHistory> currentPlan = planFacade.getCurrentPlan();
        return DisplayMapper.mapFromPlanHistoryToDisplay(currentPlan);
    }

    public List<GroceryList> getGroceryListForPlan(){
        return listOfProductsForPlan.getCurrentGroceryList();
    }
}
