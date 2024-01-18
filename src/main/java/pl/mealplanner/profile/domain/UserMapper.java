package pl.mealplanner.profile.domain;

class UserMapper {

//    public static List<PlanDto> mapFromUserToPlanDto(User user) {
//        return mapFromPlanHistoryToPlanDto(user.getPlanHistory());
//    }
//
//    public static List<PlanDto> mapFromPlanHistoryToPlanDto(List<PlanHistory> planHistoryList) {
//        return planHistoryList.stream()
//                .map(p -> PlanDto.builder()
//                        .day(p.date())
//                        .recipePlan(mapFromRecipePlanHistoryToPlanRecipeDto(p.recipePlanHistory()))
//                        .build())
//                .toList();
//    }
//
//    public static Optional<PlanRecipeDto> mapFromRecipePlanHistoryToPlanRecipeDto(RecipePlanHistory recipePlanHistory) {
//        return Optional.ofNullable(PlanRecipeDto.builder()
//                .id(recipePlanHistory.id())
//                .name(recipePlanHistory.name())
//                .portions(recipePlanHistory.portions())
//                .prepareTime(recipePlanHistory.prepareTime())
//                .diet(recipePlanHistory.diet())
//                .ingredients(mapFromIngredientPlanHistoryToIngredientDto(recipePlanHistory.ingredients()))
//                .steps(recipePlanHistory.steps())
//                .build());
//    }
//
//    public static List<IngredientDto> mapFromIngredientPlanHistoryToIngredientDto(List<IngredientPlanHistory> ingredientPlanHistory) {
//        return ingredientPlanHistory.stream()
//                .map(ing -> IngredientDto.builder()
//                        .name(ing.name())
//                        .amount(ing.amount())
//                        .unit(ing.unit())
//                        .build())
//                .toList();
//    }
}
