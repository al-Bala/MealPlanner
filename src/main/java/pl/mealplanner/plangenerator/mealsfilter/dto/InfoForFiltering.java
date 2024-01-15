package pl.mealplanner.plangenerator.mealsfilter.dto;

import lombok.Builder;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;

import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
public record InfoForFiltering(
        int forHowManyDays,
        String diet,
        int timeForPrepareMin,
        List<String> productsToUse,
        List<String> dislikedProducts
) {
}
