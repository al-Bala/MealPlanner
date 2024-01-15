package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.loginandregister.domain.entity.PlanHistory;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.UserPreferences;
import pl.mealplanner.plangenerator.domain.dto.WeekInfo;
import pl.mealplanner.plangenerator.infrastructure.dto.UserPreferencesRequest;
import pl.mealplanner.plangenerator.infrastructure.dto.WeekInfoRequest;
import pl.mealplanner.plangenerator.mealscounter.MealsCounterFacade;
import pl.mealplanner.plangenerator.mealsfilter.dto.MealPlanElement;
import pl.mealplanner.plangenerator.productscounter.ListOfProductsForPlan;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.profile.domain.User;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class PlanGeneratorFacade {

    private final MealsCounterFacade mealsCounterFacade;
    private final PlanGeneratorService planGeneratorService;
    private final ListOfProductsForPlan listOfProductsForPlan;

    public List<MealPlanElement> generateMealPlanner(UserPreferencesRequest preferencesRequest, WeekInfoRequest weekInfoRequest){
        listOfProductsForPlan.clearListOfProductsForPlan();

        UserPreferences preferences = PlanMapper.mapFromUserPreferencesRequestToUserPreferencesDto(preferencesRequest);
        WeekInfo weekInfo = PlanMapper.mapFromWeekInfoRequestToWeekInfoDto(weekInfoRequest);

        List<OneMealInfo> oneMealInfoList = mealsCounterFacade.countNumberOfMeals(weekInfo);
        InfoForMealsSearch infoForMealsSearch = InfoForMealsSearch.builder()
                .oneMealInfoList(oneMealInfoList)
                .preferencesDto(preferences)
                .build();

        List<MealPlanElement> mealPlan = planGeneratorService.generatePlan(infoForMealsSearch);
        saveAsPlanHistory(mealPlan);
        return mealPlan;
    }

    public Set<PlanProductInfo> getGroceryListForPlan(){
        return listOfProductsForPlan.getListOfProductsForPlan();
    }
//    public List<GroceryList> getGroceryListForPlan(){
//        return listOfProductsForPlan.getListOfProductsForPlan().stream()
//                .map(p -> GroceryList.builder()
//                        .name(p.getName())
//                        .packingMeasure(p.getPackingMeasure())
//                        .nrOfPackets(p.getNrOfPackets())
//                        .unit(p.getUnitCount())
//                        .build())
//                .toList();
//    }

    public User saveAsPlanHistory(List<MealPlanElement> mealPlanElement){
        List<PlanHistory> plan = mealPlanElement.stream()
                .map(meal -> PlanHistory.builder()
                        .date(meal.dayOfWeek())
                        .recipeId(meal.recipe().id())
                        .build())
                .toList();

        return planGeneratorService.saveAsPlanHistory(plan);
    }

}
