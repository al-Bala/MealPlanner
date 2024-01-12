package pl.mealplanner.plangenerator.leftproductscounter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@Component
public class ListOfProductsForPlan {
    private final LeftProductsCounterService leftProductsCounterService;
    private static final Set<PlanProductInfo> productsForPlan = new HashSet<>();

    public void clearListOfProductsForPlan(){
        productsForPlan.clear();
    }

    public Set<PlanProductInfo> getListOfProductsForPlan(){
        return productsForPlan;
    }

    public void addAll(List<PlanProductInfo> productsToUse){
        productsToUse.forEach(this::add);
    }

    public void updateProductsAfterFoundRecipe(PlanProductInfo pFromRecipe){
        for(PlanProductInfo pInList : productsForPlan) {
            if (pInList.equals(pFromRecipe)) {
                if (pFromRecipe.getAmountToUse() <= pInList.getSurplus()) {
                    setNewAmountAndSurplus(pFromRecipe, pInList);
                } else {
                    pInList.setAmountToUse(pInList.getAmountToUse() + pFromRecipe.getAmountToUse());
                    pInList.setSurplus(0);
                }
            }
        }
    }

    public void add(PlanProductInfo productToAdd){
        if(productsForPlan.isEmpty()){
            productsForPlan.add(productToAdd);
        } else {
            addAndUpdatePacking(productToAdd);
        }
    }

    private void addAndUpdatePacking(PlanProductInfo pToAdd) {
        int i = 0;
        for(PlanProductInfo pInList : productsForPlan){
            if(pInList.equals(pToAdd)){
                if(pToAdd.getAmountToUse() <= pInList.getSurplus()){
                    // odejmuje z surplus, dodaje w amount
                    setNewAmountAndSurplus(pToAdd, pInList);

                } else {
                    // jeśli pToAdd.amount > pInList.surplus to dobieranie od nowa opakowania
                    IngredientDto ingredientDto = IngredientDto.builder()
                            .name(pToAdd.getName())
                            .amount(pToAdd.getAmountToUse() + pInList.getAmountToUse())
                            .unit(pToAdd.getUnit())
                            .build();
                    PlanProductInfo updatedProduct = leftProductsCounterService.calculatePacketAndLeftovers(ingredientDto);
                    replaceProduct(pInList, updatedProduct);
                }
                break;
            }
            i++;
        }
        if(i == productsForPlan.size()){
            productsForPlan.add(pToAdd);
        }
    }

    private void setNewAmountAndSurplus(PlanProductInfo pToAdd, PlanProductInfo pInList){
        pInList.setAmountToUse(pInList.getAmountToUse() + pToAdd.getAmountToUse());
        pInList.setSurplus(pInList.getSurplus() - pToAdd.getAmountToUse());
    }

    private void replaceProduct(PlanProductInfo pInList, PlanProductInfo pForReplacement){
        if(pInList.equals(pForReplacement)){
            pInList.setAmountToUse(pForReplacement.getAmountToUse());
            pInList.setPackingMeasure(pForReplacement.getPackingMeasure());
            pInList.setNrOfPackets(pForReplacement.getNrOfPackets());
            pInList.setSurplus(pForReplacement.getSurplus());
        }
    }
}
