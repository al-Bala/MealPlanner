package pl.mealplanner.plangenerator.packingchooser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientToUseInfo;
import pl.mealplanner.plangenerator.leftproductscounter.dto.ProductMeasureInfo;
import pl.mealplanner.plangenerator.leftproductscounter.entity.PackingMeasures;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class PackingChooserFacade {

    private final PackingChooserService service;
    public IngredientToUseInfo choosePacking(Product product, IngredientDto ingRecipe){
        List<ProductMeasureInfo> biggerPackingMeasures = new ArrayList<>();
        List<ProductMeasureInfo> smallerPackingMeasures = new ArrayList<>();

        List<PackingMeasures> chosePackingMeasures = getUnit(product, ingRecipe);

        if(chosePackingMeasures.size() == 1 && chosePackingMeasures.get(0).amount() == 0){
            return IngredientToUseInfo.builder()
                    .name(ingRecipe.name())
                    .packingMeasure(ingRecipe.amount())
                    .unit(ingRecipe.unit())
                    .build();
        } else {
            for (PackingMeasures pm:chosePackingMeasures) {
                if(pm.amount() == ingRecipe.amount()){
                    return new IngredientToUseInfo(product.name(), pm.amount(), 1, 0, pm.unit());
                }
                else if(pm.amount() > ingRecipe.amount()){
                    biggerPackingMeasures.add(new ProductMeasureInfo(product.name(), pm.amount(), pm.unit()));
                }
                else{ smallerPackingMeasures.add(new ProductMeasureInfo(product.name(), pm.amount(), pm.unit()));}
            }
            return compareAndChooseTheBestPacket(biggerPackingMeasures, smallerPackingMeasures, ingRecipe);
        }
    }

    private IngredientToUseInfo compareAndChooseTheBestPacket(List<ProductMeasureInfo> biggerPackingMeasures, List<ProductMeasureInfo> smallerPackingMeasures, IngredientDto ingRecipe){
        if(smallerPackingMeasures.isEmpty()){
            return service.chooseTheBestBiggerPacket(ingRecipe.amount(), biggerPackingMeasures);
        } else if (biggerPackingMeasures.isEmpty()){
            return service.chooseTheBestSmallerPacket(ingRecipe.amount(), smallerPackingMeasures);
        } else {
            IngredientToUseInfo big = service.chooseTheBestBiggerPacket(ingRecipe.amount(), biggerPackingMeasures);
            IngredientToUseInfo small = service.chooseTheBestSmallerPacket(ingRecipe.amount(), smallerPackingMeasures);

            float bigIdk = big.nrOfPackets() * big.surplus();
            float smallIdk = small.nrOfPackets() * small.surplus();

            if(smallIdk < (0.7 * bigIdk)){
                return small;
            } else {
                return big;
            }
        }
    }

    private List<PackingMeasures> getUnit(Product product, IngredientDto ingRecipe){
        return product.packingMeasures().stream()
                .filter(p -> p.unit().equals(ingRecipe.unit()))
                .toList();
    }
}
