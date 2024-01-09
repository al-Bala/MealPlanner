package pl.mealplanner.plangenerator.packingchooser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientToUseInfo;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class PackingChooserFacade {

    private final PackingChooserService service;
    public IngredientToUseInfo choosePacking(Product product, IngredientDto ingRecipe){
        List<Integer> biggerPackingMeasures = new ArrayList<>();
        List<Integer> smallerPackingMeasures = new ArrayList<>();

        // przeliczyć ingRecipe.unit na mainUnit produktu

        List<Integer> chosePackingMeasures = product.packingMeasures();

        for (Integer amount:chosePackingMeasures) {
            if(amount == ingRecipe.amount()){
                return new IngredientToUseInfo(product.name(), amount, 1, 0, product.mainUnit());
            }
            else if(amount > ingRecipe.amount()){
                biggerPackingMeasures.add(amount);
            } else {
                smallerPackingMeasures.add(amount);
            }
        }

        MainIngToUseInfo mainIngToUseInfo = compareAndChooseTheBestPacket(biggerPackingMeasures, smallerPackingMeasures, ingRecipe);
        return IngredientToUseInfo.builder()
                .name(product.name())
                .packingMeasure(mainIngToUseInfo.packingMeasure())
                .nrOfPackets(mainIngToUseInfo.nrOfPackets())
                .surplus(mainIngToUseInfo.surplus())
                .unit(product.mainUnit())
                .build();
    }

    private MainIngToUseInfo compareAndChooseTheBestPacket(List<Integer> biggerPackingMeasures, List<Integer> smallerPackingMeasures, IngredientDto ingRecipe){
        if(smallerPackingMeasures.isEmpty()){
            return service.chooseTheBestBiggerPacket(ingRecipe.amount(), biggerPackingMeasures);
        } else if (biggerPackingMeasures.isEmpty()){
            return service.chooseTheBestSmallerPacket(ingRecipe.amount(), smallerPackingMeasures);
        } else {
            MainIngToUseInfo big = service.chooseTheBestBiggerPacket(ingRecipe.amount(), biggerPackingMeasures);
            MainIngToUseInfo small = service.chooseTheBestSmallerPacket(ingRecipe.amount(), smallerPackingMeasures);

            float bigIdk = big.nrOfPackets() * big.surplus();
            float smallIdk = small.nrOfPackets() * small.surplus();

            if(smallIdk < (0.7 * bigIdk)){
                return small;
            } else {
                return big;
            }
        }
    }
}
