package pl.mealplanner.plangenerator.domain;

import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientConverted;
import pl.mealplanner.plangenerator.mealsfilter.dto.MealPlanElement;
import pl.mealplanner.plangenerator.plan.dto.DisplayIngredient;
import pl.mealplanner.plangenerator.plan.dto.DisplayPlan;
import pl.mealplanner.plangenerator.plan.dto.DisplayRecipe;
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
                        .recipeDisplay(mapFromConvertedRecipeToRecipeDisplay(mpe.recipe()))
                        .build())
                .toList();
    }
    public static List<DisplayPlan> mapFromPlanHistoryToDisplay(List<PlanHistory> planHistoryList) {
        return planHistoryList.stream()
                .map(p -> DisplayPlan.builder()
                        .dayOfWeek(getDayOfWeek(p.date()))
                        .date(getDay(p.date()))
                        .recipeDisplay(mapFromRecipePlanHistoryToDisplayRecipe(p.recipeInPlanHistory()))
                        .build())
                .toList();
    }

    private static DisplayRecipe mapFromConvertedRecipeToRecipeDisplay(ConvertedRecipe convertedRecipe){
        if(convertedRecipe.id().equals(EMPTY_DAY_ID)){
            return DisplayRecipe.builder()
                    .id("EMPTY_DAY_ID")
                    .build();
        }
        return DisplayRecipe.builder()
                .id(convertedRecipe.id().toString())
                .name(convertedRecipe.name())
                .portions(convertedRecipe.portions())
                .prepare_time(convertedRecipe.prepare_time())
                .ingredients(mapFromIngredientConvertedToIngredientDisplay(convertedRecipe.ingredients()))
                .steps(convertedRecipe.steps())
                .build();
    }

    private static List<DisplayIngredient> mapFromIngredientConvertedToIngredientDisplay(List<IngredientConverted> ingConverted){
        return ingConverted.stream()
                .map(ing -> DisplayIngredient.builder()
                        .name(ing.name())
                        .amountDisplay(roundAmount(ing.amountsAndUnit().getAmountDisplay()))
                        .unitDisplay(ing.amountsAndUnit().getUnitDisplay())
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

    private static DisplayRecipe mapFromRecipePlanHistoryToDisplayRecipe(RecipeInPlanHistory recipeInPlanHistory) {
        if(recipeInPlanHistory.id().equals(EMPTY_DAY_ID)){
            return DisplayRecipe.builder()
                    .id("EMPTY_DAY_ID")
                    .build();
        }
        return DisplayRecipe.builder()
                .id(recipeInPlanHistory.id().toString())
                .name(recipeInPlanHistory.name())
                .portions(recipeInPlanHistory.portions())
                .prepare_time(recipeInPlanHistory.prepareTime())
//                .diet(recipeInPlanHistory.diet())
                .ingredients(mapFromIngredientPlanHistoryToDisplayIngredient(recipeInPlanHistory.ingredients()))
                .steps(recipeInPlanHistory.steps())
                .build();
    }

    private static List<DisplayIngredient> mapFromIngredientPlanHistoryToDisplayIngredient(List<IngredientPlanHistory> ingredientPlanHistory) {
        return ingredientPlanHistory.stream()
                .map(ing -> DisplayIngredient.builder()
                        .name(ing.name())
                        .amountDisplay(roundAmount(ing.amount()))
                        .unitDisplay(ing.unit())
                        .build())
                .toList();
    }
}
