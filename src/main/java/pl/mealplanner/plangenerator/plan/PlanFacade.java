package pl.mealplanner.plangenerator.plan;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.MealPlanElement;
import pl.mealplanner.plangenerator.productscounter.dto.GroceryList;
import pl.mealplanner.profile.domain.UserFacade;
import pl.mealplanner.profile.domain.entity.PlanHistory;
import pl.mealplanner.profile.domain.entity.RecipeInPlanHistory;
import pl.mealplanner.profile.domain.entity.User;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Component
public class PlanFacade {

    private final UserFacade userFacade;
    public final static ObjectId EMPTY_DAY_ID = new ObjectId("454D5054595F4441595F4944");

    public List<PlanHistory> getCurrentPlan() {
        User currentUser = userFacade.getCurrentUser();
        List<PlanHistory> planHistory = currentUser.getPlanHistory();
        if (planHistory == null) {
            return Collections.emptyList();
        }
        return planHistory;
    }

    public RecipeInPlanHistory getRecipeFromPlanById(String id) {
        User currentUser = userFacade.getCurrentUser();
        return currentUser.getPlanHistory().stream()
                .filter(r -> r.recipeInPlanHistory().id().toString().equals(id))
                .findAny()
                .orElseThrow().recipeInPlanHistory();
    }

    public void savePlanAndGroceryList(List<MealPlanElement> mealPlanElement, List<GroceryList> groceryList){
        List<PlanHistory> plan = mealPlanElement.stream()
                .map(this::convertToPlanHistory)
                .toList();

        String username = userFacade.authenticate();
        userFacade.updateUserPlanAndGroceryList(username, plan, groceryList);
    }

    private PlanHistory convertToPlanHistory(MealPlanElement mealPlanElement){
        return PlanHistory.builder()
                .date(mealPlanElement.dayOfWeek())
                .recipeInPlanHistory(PlanMapper.mapFromConvertedRecipeToRecipeInPlanHistory(mealPlanElement.recipe()))
                .build();
    }
}
