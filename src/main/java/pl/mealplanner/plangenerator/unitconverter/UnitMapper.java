package pl.mealplanner.plangenerator.unitconverter;

import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientConverted;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;

import java.util.List;

class UnitMapper {
    public static List<PlanProductInfo> mapForIncludeUserProductsToUse(List<IngredientConverted> productsToUse) {
        // ustawia userProductsToUse jako nadwyżka do wykorzystania
        return productsToUse.stream()
                .map(p -> PlanProductInfo.builder()
                        .name(p.name())
                        .amountToUseCount(0)
                        .packingMeasure(0)
                        .nrOfPackets(0)
                        .surplus(p.amountsAndUnit().getAmountCount())
                        .unitCount(p.amountsAndUnit().getUnitCount())
                        .build())
                .toList();
    }
}
