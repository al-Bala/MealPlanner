package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.*;
import pl.mealplanner.plangenerator.infrastructure.dto.UserPreferencesRequest;
import pl.mealplanner.plangenerator.infrastructure.dto.WeekInfoRequest;
import pl.mealplanner.plangenerator.leftproductscounter.GroceryList;
import pl.mealplanner.plangenerator.leftproductscounter.dto.ShoppingInfo;
import pl.mealplanner.plangenerator.mealscounter.MealsCounterFacade;
import pl.mealplanner.plangenerator.mealsfilter.MealsFilterFacade;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.List;

@AllArgsConstructor
@Component
public class PlanGeneratorFacade {

    private final MealsCounterFacade mealsCounterFacade;
    private final MealsFilterFacade mealsFilterFacade;
    private final GroceryList groceryList;

    public List<FilteredRecipeDto> generateMealPlanner(UserPreferencesRequest preferencesRequest, WeekInfoRequest weekInfoRequest){
        groceryList.clearGroceryList();

        UserPreferencesDto preferences = PlanMapper.mapFromUserPreferencesRequestToUserPreferencesDto(preferencesRequest);
        WeekInfoDto weekInfo = PlanMapper.mapFromWeekInfoRequestToWeekInfoDto(weekInfoRequest);

        List<OneMealInfo> oneMealInfoList = mealsCounterFacade.countNumberOfMeals(weekInfo);
        InfoForMealsSearch infoForMealsSearch = InfoForMealsSearch.builder()
                .oneMealInfoList(oneMealInfoList)
                .preferencesDto(preferences)
                .build();

        return mealsFilterFacade.findRecipes(infoForMealsSearch);
    }

    public List<ShoppingInfo> getGroceryListForPlan(){
        return groceryList.getGroceryList();
    }
}
