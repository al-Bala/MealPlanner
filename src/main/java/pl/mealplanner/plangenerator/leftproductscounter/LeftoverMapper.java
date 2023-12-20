package pl.mealplanner.plangenerator.leftproductscounter;

import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientsToUseInfo;
import pl.mealplanner.plangenerator.leftproductscounter.dto.Leftover;
import pl.mealplanner.plangenerator.leftproductscounter.dto.ShoppingInfo;

class LeftoverMapper {

    public static Leftover mapFromIngredientsToUseInfoToLeftover(IngredientsToUseInfo info) {
        return Leftover.builder()
                .name(info.name())
                .surplus(info.surplus())
                .unit(info.unit())
                .build();
    }

    public static ShoppingInfo mapFromIngredientsToUseInfoToShoppingInfo(IngredientsToUseInfo ing) {
        return ShoppingInfo.builder()
                .name(ing.name())
                .packingMeasure(ing.packingMeasure())
                .nrOfPackets(ing.nrOfPackets())
                .unit(ing.unit())
                .build();
    }
}
