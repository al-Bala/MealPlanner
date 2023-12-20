package pl.mealplanner.plangenerator.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.UserPreferencesDto;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoDto;
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

    public List<FilteredRecipeDto> generateMealPlanner(UserPreferencesDto preferencesDto, WeekInfoDto weekInfoDto){
        groceryList.clearGroceryList();

        List<OneMealInfo> oneMealInfoList = mealsCounterFacade.countNumberOfMeals(weekInfoDto);

        InfoForMealsSearch infoForMealsSearch = InfoForMealsSearch.builder()
                .oneMealInfoList(oneMealInfoList)
                .preferencesDto(preferencesDto)
                .build();

        return mealsFilterFacade.findRecipes(infoForMealsSearch);
    }

    public List<ShoppingInfo> getGroceryListForPlan(){
        return groceryList.getGroceryList();
    }
}
