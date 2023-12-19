package pl.mealplanner.plangenerator.packingchooser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientsToUseInfo;
import pl.mealplanner.plangenerator.leftproductscounter.dto.ProductMeasureInfo;
import pl.mealplanner.plangenerator.leftproductscounter.entity.PackingMeasures;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class PackingChooserFacade {

    private final PackingChooserService service;

    public IngredientsToUseInfo choosePacking(Product product, float recipeAmount){
        List<ProductMeasureInfo> biggerPackingMeasures = new ArrayList<>();
        List<ProductMeasureInfo> smallerPackingMeasures = new ArrayList<>();

        for (PackingMeasures pm:product.packingMeasures()
        ) {
            if(pm.amount() == recipeAmount){
                return new IngredientsToUseInfo(product.name(), pm.amount(), 1, 0, pm.unit());
            }
            else if(pm.amount() > recipeAmount){
                biggerPackingMeasures.add(new ProductMeasureInfo(product.name(), pm.amount(), pm.unit()));
            }
            else{
                smallerPackingMeasures.add(new ProductMeasureInfo(product.name(), pm.amount(), pm.unit()));
            }
        }

        if(smallerPackingMeasures.isEmpty()){
            return service.chooseTheBestBiggerPacket(recipeAmount, biggerPackingMeasures);
        } else if (biggerPackingMeasures.isEmpty()){
            return service.chooseTheBestSmallerPacket(recipeAmount, smallerPackingMeasures);
        } else {
            IngredientsToUseInfo big = service.chooseTheBestBiggerPacket(recipeAmount, biggerPackingMeasures);
            IngredientsToUseInfo small = service.chooseTheBestSmallerPacket(recipeAmount, smallerPackingMeasures);

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
