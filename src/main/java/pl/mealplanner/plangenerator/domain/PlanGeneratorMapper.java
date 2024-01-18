package pl.mealplanner.plangenerator.domain;

import pl.mealplanner.plangenerator.domain.dto.*;
import pl.mealplanner.plangenerator.infrastructure.dto.*;
import pl.mealplanner.plangenerator.domain.dto.IngredientDto;

import java.util.Collections;
import java.util.List;

class PlanGeneratorMapper {
    public static UserPreferences mapFromUserPreferencesRequestToUserPreferencesDto(UserPreferencesRequest request) {
        return UserPreferences.builder()
                .numberOfPortions(request.getNumberOfPortions())
                .diet(request.getDiet())
                .productsToUse(mapFromIngredientRequestToIngredientDto(request.getProductsToUse()))
                .dislikedProducts(mapFromDislikedProductDtoToString(request.getDislikedProducts()))
                .build();
    }

    private static List<IngredientDto> mapFromIngredientRequestToIngredientDto(List<IngredientRequest> ingredientRequests){
        if(ingredientRequests == null){
            return Collections.emptyList();
        }
        return ingredientRequests.stream()
                .map(ing -> IngredientDto.builder()
                        .name(ing.getName())
                        .amount(ing.getAmount())
                        .unit(ing.getUnit())
                        .build())
                .toList();
    }
    private static List<String> mapFromDislikedProductDtoToString(List<DislikedProductRequest> dislikedProductsRequests){
        if(dislikedProductsRequests == null){
            return Collections.emptyList();
        }
        return dislikedProductsRequests.stream()
                .map(DislikedProductRequest::getName)
                .toList();
    }

    public static WeekInfo mapFromWeekInfoRequestToWeekInfoDto(WeekInfoRequest request) {
        return WeekInfo.builder()
                .dayInfoList(mapFromDayInfoRequestToDayInfo(request.getDayInfoList()))
                .build();
    }

    private static List<DayInfo> mapFromDayInfoRequestToDayInfo(List<DayInfoRequest> dayInfoRequests){
        return dayInfoRequests.stream()
                .map(dayInfo -> DayInfo.builder()
                        .day(dayInfo.getDay())
                        .eatingPlan(mapFromEatingPlanToEatingPlanDto(dayInfo.getEatingPlan()))
                        .build())
                .toList();
    }

    private static EatingPlans mapFromEatingPlanToEatingPlanDto(EatingPlansRequest eatingPlansRequest){
        return EatingPlans.builder()
                .id(eatingPlansRequest.getId())
                .timeMin(eatingPlansRequest.getTimeMin())
                .build();
    }
}
