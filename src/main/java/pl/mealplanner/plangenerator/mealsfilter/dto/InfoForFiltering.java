package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;

import java.util.List;
import java.util.Set;

@Builder
public record InfoForFiltering(
        int forHowManyDays,
        String diet,
        int timeForPrepareMin,
        Set<PlanProductInfo> productsToUse,
        List<String> dislikedProducts
) {
}
