package pl.mealplanner.plangenerator.packingchooser;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class PackingChooserService {
    public MainIngToUseInfo chooseTheBestBiggerPacket(float recipeAmount, List<Integer> listOfMeasures){
        List<MainIngToUseInfo> array = new ArrayList<>();

        for (Integer packingMeasure : listOfMeasures) {
//            int nrOfPackets = (int)(beforeCounting.amount() / recipeAmount);
            float surplus = packingMeasure - recipeAmount;
            array.add(MainIngToUseInfo.builder()
                    .packingMeasure(packingMeasure)
                    .nrOfPackets(1)
                    .surplus(getSurplus(surplus))
                    .build());
        }
        return getMinSurplus(array);
    }

    public MainIngToUseInfo chooseTheBestSmallerPacket(float recipeAmount, List<Integer> listOfMeasures) {
        List<MainIngToUseInfo> array = new ArrayList<>();

        for (Integer packingMeasure : listOfMeasures) {
            int nrOfPackets = (int) (recipeAmount / packingMeasure);
            float theRest = recipeAmount % packingMeasure;
            float surplus = packingMeasure - theRest;
            array.add(MainIngToUseInfo.builder()
                    .packingMeasure(packingMeasure)
                    .nrOfPackets(nrOfPackets + 1)
                    .surplus(getSurplus(surplus))
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

    private MainIngToUseInfo getMinSurplus(List<MainIngToUseInfo> array) {
        return array.stream()
                .min((f1, f2) -> Float.compare(f1.surplus(), f2.surplus()))
                .orElse(null);
    }
}
