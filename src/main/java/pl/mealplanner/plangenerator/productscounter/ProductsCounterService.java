package pl.mealplanner.plangenerator.productscounter;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.productscounter.dto.MainIngToUseInfo;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.productscounter.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Log4j2
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
        try{
            return repository.findByName(name)
                    .orElseThrow(() -> new NoProductFoundException("Product: " + name + ", not found"));
        } catch (NoProductFoundException e){
            log.error(e.getMessage());
        }
        return null;
    }

    private PlanProductInfo choosePacketAndCountLeftovers(Product product, float ingAmount){
        List<Integer> biggerPackingMeasures = new ArrayList<>();
        List<Integer> smallerPackingMeasures = new ArrayList<>();

        int convertedRecipeAmount = (int) ingAmount;
        List<Integer> chosePackingMeasures = product.packingMeasures();

        if(chosePackingMeasures.isEmpty()){
            return PlanProductInfo.builder()
                    .name(product.name())
                    .amountToUseCount(convertedRecipeAmount)
                    .packingMeasure(convertedRecipeAmount)
                    .nrOfPackets(1)
                    .surplus(0)
                    .unitCount(product.mainUnit())
                    .build();
        }

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

