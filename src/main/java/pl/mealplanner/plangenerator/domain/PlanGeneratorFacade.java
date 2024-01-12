package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.*;
import pl.mealplanner.plangenerator.infrastructure.dto.UserPreferencesRequest;
import pl.mealplanner.plangenerator.infrastructure.dto.WeekInfoRequest;
import pl.mealplanner.plangenerator.leftproductscounter.ListOfProductsForPlan;
import pl.mealplanner.plangenerator.leftproductscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.mealscounter.MealsCounterFacade;
import pl.mealplanner.plangenerator.mealsfilter.MealsFilterFacade;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class PlanGeneratorFacade {

    private final MealsCounterFacade mealsCounterFacade;
    private final MealsFilterFacade mealsFilterFacade;
    private final ListOfProductsForPlan listOfProductsForPlan;

    public List<FilteredRecipeDto> generateMealPlanner(UserPreferencesRequest preferencesRequest, WeekInfoRequest weekInfoRequest){
        listOfProductsForPlan.clearListOfProductsForPlan();

        UserPreferencesDto preferences = PlanMapper.mapFromUserPreferencesRequestToUserPreferencesDto(preferencesRequest);
        WeekInfoDto weekInfo = PlanMapper.mapFromWeekInfoRequestToWeekInfoDto(weekInfoRequest);

        List<OneMealInfo> oneMealInfoList = mealsCounterFacade.countNumberOfMeals(weekInfo);
        InfoForMealsSearch infoForMealsSearch = InfoForMealsSearch.builder()
                .oneMealInfoList(oneMealInfoList)
                .preferencesDto(preferences)
                .build();

        System.out.println(mealsFilterFacade.findRecipes(infoForMealsSearch));
        System.out.println(getGroceryListForPlan());
        return null;
    }

    public Set<PlanProductInfo> getGroceryListForPlan(){
        return listOfProductsForPlan.getListOfProductsForPlan();
    }
}
