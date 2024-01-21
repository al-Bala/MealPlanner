package pl.mealplanner.plangenerator.domain;

import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;
import pl.mealplanner.plangenerator.mealsfilter.dto.MealPlanElement;
import pl.mealplanner.plangenerator.plan.dto.DisplayIngredient;
import pl.mealplanner.plangenerator.plan.dto.DisplayPlan;
import pl.mealplanner.plangenerator.plan.dto.DisplayRecipe;
import pl.mealplanner.plangenerator.plan.dto.FirstDisplayRecipe;
import pl.mealplanner.profile.domain.entity.IngredientPlanHistory;
import pl.mealplanner.profile.domain.entity.PlanHistory;
import pl.mealplanner.profile.domain.entity.RecipeInPlanHistory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static pl.mealplanner.plangenerator.plan.PlanFacade.EMPTY_DAY_ID;

class DisplayMapper {

    public static List<DisplayPlan> mapFromMealPlanElementToDisplay(List<MealPlanElement> mealPlan) {
        return mealPlan.stream()
                .map(mpe -> DisplayPlan.builder()
                        .dayOfWeek(getDayOfWeek(mpe.dayOfWeek()))
                        .date(getDay(mpe.dayOfWeek()))
                        .firstDisplayRecipe(mapFromConvertedRecipeToFirstDisplayRecipe(mpe.recipe()))
                        .build())
                .toList();
    }
    public static List<DisplayPlan> mapFromPlanHistoryToDisplay(List<PlanHistory> planHistoryList) {
        return planHistoryList.stream()
                .map(p -> DisplayPlan.builder()
                        .dayOfWeek(getDayOfWeek(p.date()))
                        .date(getDay(p.date()))
                        .firstDisplayRecipe(mapFromRecipePlanHistoryToFirstDisplayRecipe(p.recipeInPlanHistory()))
                        .build())
                .toList();
    }
    public static DisplayRecipe mapFromRecipeInPlanHistoryToDisplay(RecipeInPlanHistory recipe) {
        if(recipe.id().equals(EMPTY_DAY_ID)){
            return DisplayRecipe.builder()
                    .id("EMPTY_DAY_ID")
                    .build();
        }
        return DisplayRecipe.builder()
                .id(recipe.id().toString())
                .name(recipe.name())
                .portions(recipe.portions())
                .prepare_time(recipe.prepareTime())
                .diet(recipe.diet())
                .ingredients(mapFromIngredientPlanHistoryToIngredientDisplay(recipe.ingredients()))
                .steps(recipe.steps())
                .build();
    }


    private static FirstDisplayRecipe mapFromConvertedRecipeToFirstDisplayRecipe(ConvertedRecipe convertedRecipe){
        if(convertedRecipe.id().equals(EMPTY_DAY_ID)){
            return FirstDisplayRecipe.builder()
                    .id("EMPTY_DAY_ID")
                    .build();
        }
        return FirstDisplayRecipe.builder()
                .id(convertedRecipe.id().toString())
                .name(convertedRecipe.name())
                .prepareTime(convertedRecipe.prepare_time())
                .build();
    }

    private static List<DisplayIngredient> mapFromIngredientPlanHistoryToIngredientDisplay(List<IngredientPlanHistory> ingPlan){
        return ingPlan.stream()
                .map(ing -> DisplayIngredient.builder()
                        .name(ing.name())
                        .amount(roundAmount(ing.amount()))
                        .unit(ing.unit())
                        .build())
                .toList();
    }

    private static float roundAmount(float amount){
        return Math.round(amount * 10.0) / 10.0f;
    }
    private static String getDayOfWeek(LocalDate day){
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("EEEE", new Locale("pl"));
        return day.format(formatterDay).substring(0, 1).toUpperCase() + day.format(formatterDay).substring(1);
    }
    private static String getDay(LocalDate day){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return day.format(formatterDate);
    }

    private static FirstDisplayRecipe mapFromRecipePlanHistoryToFirstDisplayRecipe(RecipeInPlanHistory recipeInPlanHistory) {
        if(recipeInPlanHistory.id().equals(EMPTY_DAY_ID)){
            return FirstDisplayRecipe.builder()
                    .id("EMPTY_DAY_ID")
                    .build();
        }
        return FirstDisplayRecipe.builder()
                .id(recipeInPlanHistory.id().toString())
                .name(recipeInPlanHistory.name())
                .prepareTime(recipeInPlanHistory.prepareTime())
                .build();
    }
}
