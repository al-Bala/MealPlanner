package pl.mealplanner.plangenerator.packingchooser;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Log4j2
@Component
public class PackingChooserFacade {

    private final PackingChooserService service;
    public PlanProductInfo choosePacking(Product product, IngredientDto ingRecipe){
        List<Integer> biggerPackingMeasures = new ArrayList<>();
        List<Integer> smallerPackingMeasures = new ArrayList<>();

        int convertedRecipeAmount = convertUnit(product, ingRecipe);

        // przeliczyć ingRecipe.unit na mainUnit produktu

        List<Integer> chosePackingMeasures = product.packingMeasures();

        for (Integer packingMeasure:chosePackingMeasures) {
            if(packingMeasure == convertedRecipeAmount){
                return PlanProductInfo.builder()
                        .name(product.name())
                        .amountToUse(convertedRecipeAmount)
                        .packingMeasure(packingMeasure)
                        .nrOfPackets(1)
                        .surplus(0)
                        .unit(product.mainUnit())
                        .build();
            }
            else if(packingMeasure > convertedRecipeAmount){
                biggerPackingMeasures.add(packingMeasure);
            } else {
                smallerPackingMeasures.add(packingMeasure);
            }
        }

        MainIngToUseInfo mainIngToUseInfo = compareAndChooseTheBestPacket(biggerPackingMeasures, smallerPackingMeasures, convertedRecipeAmount);
        return PlanProductInfo.builder()
                .name(product.name())
                .amountToUse(convertedRecipeAmount)
                .packingMeasure(mainIngToUseInfo.packingMeasure())
                .nrOfPackets(mainIngToUseInfo.nrOfPackets())
                .surplus(mainIngToUseInfo.surplus())
                .unit(product.mainUnit())
                .build();
    }

    private int convertUnit(Product product, IngredientDto ingRecipe){
        switch (ingRecipe.unit()){
            case "g", "ml": return (int) ingRecipe.amount();
            case "kg", "l": return (int) ingRecipe.amount() * 1000;
            case "łyżeczka" : return (int) ingRecipe.amount() * 5;
            case "łyżka" : return (int) ingRecipe.amount() * 15;
            case "szczypta" : return 0;
            case "szt" : {
                if(product.packingUnits().size() == 1){
                    return (int) ingRecipe.amount();
                } else {
                    if(product.packingMeasures().size() == 1){
                        // zał: jak produkt jest na sztuki to w bazie jest podana tylko waga 1 szt, nic więcej
                        return (int) ingRecipe.amount() * product.packingMeasures().get(0);
                    }
                    log.warn("Brak jednoznacznie zdefiniowanej wagi dla szt produktu: " + product.name());
                    return 0;
                }
            }
        }
        return -1;
    }

    private MainIngToUseInfo compareAndChooseTheBestPacket(List<Integer> biggerPackingMeasures, List<Integer> smallerPackingMeasures, int convertedRecipeAmount){
        if(smallerPackingMeasures.isEmpty()){
            return service.chooseTheBestBiggerPacket(convertedRecipeAmount, biggerPackingMeasures);
        } else if (biggerPackingMeasures.isEmpty()){
            return service.chooseTheBestSmallerPacket(convertedRecipeAmount, smallerPackingMeasures);
        } else {
            MainIngToUseInfo big = service.chooseTheBestBiggerPacket(convertedRecipeAmount, biggerPackingMeasures);
            MainIngToUseInfo small = service.chooseTheBestSmallerPacket(convertedRecipeAmount, smallerPackingMeasures);

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
