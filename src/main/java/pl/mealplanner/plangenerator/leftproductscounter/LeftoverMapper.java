package pl.mealplanner.plangenerator.leftproductscounter;

import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientsToUseInfo;
import pl.mealplanner.plangenerator.leftproductscounter.dto.Leftover;

class LeftoverMapper {

    public static Leftover mapFromIngredientsToUseInfoToLeftover(IngredientsToUseInfo info) {
        return Leftover.builder()
                .name(info.name())
                .surplus(info.surplus())
                .unit(info.unit())
                .build();
    }

}
