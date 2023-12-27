package pl.mealplanner.plangenerator.leftproductscounter;

import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientToUseInfo;
import pl.mealplanner.plangenerator.leftproductscounter.dto.Leftover;
import pl.mealplanner.plangenerator.leftproductscounter.dto.ShoppingInfo;

class LeftoverMapper {

    public static Leftover mapFromIngredientsToUseInfoToLeftover(IngredientToUseInfo info) {
        return Leftover.builder()
                .name(info.name())
                .surplus(info.surplus())
                .unit(info.unit())
                .build();
    }

    public static ShoppingInfo mapFromIngredientsToUseInfoToShoppingInfo(IngredientToUseInfo ing) {
        return ShoppingInfo.builder()
                .name(ing.name())
                .packingMeasure(ing.packingMeasure())
                .nrOfPackets(ing.nrOfPackets())
                .unit(ing.unit())
                .build();
    }
}
