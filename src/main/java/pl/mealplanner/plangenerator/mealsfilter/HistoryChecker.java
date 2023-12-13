package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.mealplanner.loginandregister.domain.LoginAndRegisterFacade;
import pl.mealplanner.loginandregister.domain.dto.PlanHistoryDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.List;

@AllArgsConstructor
@Component
class HistoryChecker {

    private final LoginAndRegisterFacade loginAndRegisterFacade;

    public FilteredRecipeDto checkPreviousWeek(List<FilteredRecipeDto> currentFoundMeals){
        String username = authenticate();
        List<ObjectId> pastPlanRecipesIdList = getRecipeIdListFromPlanHistory(username);
        return choseRecipeWhichWasNotInPlan(currentFoundMeals, pastPlanRecipesIdList);
    }

    private String authenticate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private List<ObjectId> getRecipeIdListFromPlanHistory(String username){
        List<PlanHistoryDto> planHistory = loginAndRegisterFacade.findPlanHistoryByUsername(username).planHistoryList();
         return planHistory.stream()
                .map(PlanHistoryDto::recipeId)
                .toList();
    }

    private FilteredRecipeDto choseRecipeWhichWasNotInPlan(List<FilteredRecipeDto> currentFoundMeals, List<ObjectId> pastPlanRecipesIdList){
        for (FilteredRecipeDto recipe:currentFoundMeals) {
            if(!pastPlanRecipesIdList.contains(recipe.id())) {
                return recipe;
            }
        }
        // jeśli wszystkie z listy się powtarzają
        return currentFoundMeals.get(0);
    }
}
