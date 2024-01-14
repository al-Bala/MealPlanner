package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.*;
import pl.mealplanner.plangenerator.infrastructure.dto.UserPreferencesRequest;
import pl.mealplanner.plangenerator.infrastructure.dto.WeekInfoRequest;
import pl.mealplanner.plangenerator.productscounter.ListOfProductsForPlan;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.mealscounter.MealsCounterFacade;
import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class PlanGeneratorFacade {

    private final MealsCounterFacade mealsCounterFacade;
//    private final MealsFilterFacade mealsFilterFacade;
    private final PlanGeneratorService planGeneratorService;
    private final ListOfProductsForPlan listOfProductsForPlan;

    public List<ConvertedRecipe> generateMealPlanner(UserPreferencesRequest preferencesRequest, WeekInfoRequest weekInfoRequest){
        listOfProductsForPlan.clearListOfProductsForPlan();

        UserPreferences preferences = PlanMapper.mapFromUserPreferencesRequestToUserPreferencesDto(preferencesRequest);
        WeekInfo weekInfo = PlanMapper.mapFromWeekInfoRequestToWeekInfoDto(weekInfoRequest);

        List<OneMealInfo> oneMealInfoList = mealsCounterFacade.countNumberOfMeals(weekInfo);
        InfoForMealsSearch infoForMealsSearch = InfoForMealsSearch.builder()
                .oneMealInfoList(oneMealInfoList)
                .preferencesDto(preferences)
                .build();

        return planGeneratorService.generatePlan(infoForMealsSearch);
//        return mealsFilterFacade.generatePlan(infoForMealsSearch);
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
}
