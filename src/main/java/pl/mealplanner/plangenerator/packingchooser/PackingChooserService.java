package pl.mealplanner.plangenerator.packingchooser;

import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientToUseInfo;
import pl.mealplanner.plangenerator.leftproductscounter.dto.ProductMeasureInfo;

import java.util.ArrayList;
import java.util.List;

@Component
class PackingChooserService {
    public IngredientToUseInfo chooseTheBestBiggerPacket(float recipeAmount, List<ProductMeasureInfo> listOfMeasures){
        List<IngredientToUseInfo> array = new ArrayList<>();

        for (ProductMeasureInfo productMeasureInfo :listOfMeasures) {
//            int nrOfPackets = (int)(beforeCounting.amount() / recipeAmount);
            float surplus = productMeasureInfo.amount() - recipeAmount;
            array.add(IngredientToUseInfo.builder()
                    .name(productMeasureInfo.name())
                    .packingMeasure(productMeasureInfo.amount())
                    .nrOfPackets(1)
                    .surplus(getSurplus(surplus))
                    .unit(productMeasureInfo.unit())
                    .build());
        }
        return getMinSurplus(array);
    }

    public IngredientToUseInfo chooseTheBestSmallerPacket(float recipeAmount, List<ProductMeasureInfo> listOfMeasures) {
        List<IngredientToUseInfo> array = new ArrayList<>();

        for (ProductMeasureInfo productMeasureInfo : listOfMeasures) {
            int nrOfPackets = (int) (recipeAmount / productMeasureInfo.amount());
            float theRest = recipeAmount % productMeasureInfo.amount();
            float surplus = productMeasureInfo.amount() - theRest;
            array.add(IngredientToUseInfo.builder()
                    .name(productMeasureInfo.name())
                    .packingMeasure(productMeasureInfo.amount())
                    .nrOfPackets(nrOfPackets + 1)
                    .surplus(getSurplus(surplus))
                    .unit(productMeasureInfo.unit())
                    .build());
        }
        return getMinSurplus(array);
    }

    private float getSurplus(float surplus){
        if(surplus <= 50f){
            return 0f;
        } else {
            return surplus;
        }
    }

    private IngredientToUseInfo getMinSurplus(List<IngredientToUseInfo> array) {
        return array.stream()
                .min((f1, f2) -> Float.compare(f1.surplus(), f2.surplus()))
                .orElse(null);
    }
}
