package pl.mealplanner.plangenerator.productscounter;

import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.productscounter.dto.MainIngToUseInfo;

import java.util.ArrayList;
import java.util.List;

@Component
class PackingChooser {

    public MainIngToUseInfo compareAndChooseTheBestPacket(List<Integer> biggerPackingMeasures, List<Integer> smallerPackingMeasures, int convertedRecipeAmount){
        if(smallerPackingMeasures.isEmpty()){
            return chooseTheBestBiggerPacket(convertedRecipeAmount, biggerPackingMeasures);
        } else if (biggerPackingMeasures.isEmpty()){
            return chooseTheBestSmallerPacket(convertedRecipeAmount, smallerPackingMeasures);
        } else {
            MainIngToUseInfo big = chooseTheBestBiggerPacket(convertedRecipeAmount, biggerPackingMeasures);
            MainIngToUseInfo small = chooseTheBestSmallerPacket(convertedRecipeAmount, smallerPackingMeasures);

            float bigIdk = big.nrOfPackets() * big.surplus();
            float smallIdk = small.nrOfPackets() * small.surplus();

            if(smallIdk < (0.7 * bigIdk)){
                return small;
            } else {
                return big;
            }
        }
    }
    private MainIngToUseInfo chooseTheBestBiggerPacket(float recipeAmount, List<Integer> listOfMeasures){
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

    private MainIngToUseInfo chooseTheBestSmallerPacket(float recipeAmount, List<Integer> listOfMeasures) {
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

    // TODO: nie 50
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
