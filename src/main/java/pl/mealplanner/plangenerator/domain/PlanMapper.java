package pl.mealplanner.plangenerator.domain;

import pl.mealplanner.plangenerator.domain.dto.*;
import pl.mealplanner.plangenerator.infrastructure.dto.*;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.Collections;
import java.util.List;

class PlanMapper {
    public static UserPreferencesDto mapFromUserPreferencesRequestToUserPreferencesDto(UserPreferencesRequest request) {
        return UserPreferencesDto.builder()
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

    public static WeekInfoDto mapFromWeekInfoRequestToWeekInfoDto(WeekInfoRequest request) {
        return WeekInfoDto.builder()
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

    private static EatingPlansDto mapFromEatingPlanToEatingPlanDto(EatingPlansRequest eatingPlansRequest){
        return EatingPlansDto.builder()
                .id(eatingPlansRequest.getId())
                .timeMin(eatingPlansRequest.getTimeMin())
                .build();
    }
}
