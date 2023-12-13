package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.mealplanner.loginandregister.domain.LoginAndRegisterFacade;
import pl.mealplanner.loginandregister.domain.dto.PlanHistoryDto;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;

import java.util.List;

@AllArgsConstructor
@Service
class MealsFilterService {

    private final MealsFinder mealsFinder;
    private final HistoryChecker historyChecker;
    private final IngredientsCalculator ingredientsCalculator;

    public List<FilteredRecipeDto> findMeals(InfoForMealsSearch infoForMealsSearch){

        List<FilteredRecipeDto> allRecipesForPlan = getInfoForFiltering(infoForMealsSearch).stream()
                        .map(mealsFinder::findMatchingRecipes)
                        .map(historyChecker::checkPreviousWeek)
                        .toList();

        return allRecipesForPlan;
    }

    private List<InfoForFiltering> getInfoForFiltering(InfoForMealsSearch infoForMealsSearch){
        return infoForMealsSearch.oneMealInfoList().stream()
                .map(oneMealInfo -> InfoForFiltering.builder()
                        .forHowManyDays(oneMealInfo.forHowManyDays())
                        .diet(infoForMealsSearch.preferencesDto().diet())
                        .timeForPrepareMin(oneMealInfo.timeForPrepareMin())
                        .productsToUse(infoForMealsSearch.preferencesDto().productsToUse())
                        .dislikedProducts(infoForMealsSearch.preferencesDto().dislikedProducts())
                        .build())
                .toList();
    }
    String saveTheLastLeftovers(){
        return null;
    }
}
