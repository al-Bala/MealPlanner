package pl.mealplanner.plangenerator.productscounter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.productscounter.dto.MainIngToUseInfo;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.productscounter.entity.Product;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
class ProductsCounterService {

    private final ProductsCounterRepository repository;
    private final PackingChooser service;

    public PlanProductInfo calculateForOneProduct(String ingName, float ingAmount){
        Product product = findProduct(ingName);
        return choosePacketAndCountLeftovers(product, ingAmount);
    }

    public Product findProduct(String name){
        return repository.findByName(name)
                .orElseThrow();
    }

    private PlanProductInfo choosePacketAndCountLeftovers(Product product, float ingAmount){
        List<Integer> biggerPackingMeasures = new ArrayList<>();
        List<Integer> smallerPackingMeasures = new ArrayList<>();

        int convertedRecipeAmount = (int) ingAmount;
        List<Integer> chosePackingMeasures = product.packingMeasures();

        for (Integer packingMeasure:chosePackingMeasures) {
            if(packingMeasure == convertedRecipeAmount){
                return PlanProductInfo.builder()
                        .name(product.name())
                        .amountToUseCount(convertedRecipeAmount)
                        .packingMeasure(packingMeasure)
                        .nrOfPackets(1)
                        .surplus(0)
                        .unitCount(product.mainUnit())
                        .build();
            }
            else if(packingMeasure > convertedRecipeAmount){
                biggerPackingMeasures.add(packingMeasure);
            } else {
                smallerPackingMeasures.add(packingMeasure);
            }
        }

        MainIngToUseInfo mainIngToUseInfo = service.compareAndChooseTheBestPacket(biggerPackingMeasures, smallerPackingMeasures, convertedRecipeAmount);
        return PlanProductInfo.builder()
                .name(product.name())
                .amountToUseCount(convertedRecipeAmount)
                .packingMeasure(mainIngToUseInfo.packingMeasure())
                .nrOfPackets(mainIngToUseInfo.nrOfPackets())
                .surplus(mainIngToUseInfo.surplus())
                .unitCount(product.mainUnit())
                .build();
    }
}

