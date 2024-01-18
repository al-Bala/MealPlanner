package pl.mealplanner.plangenerator.productscounter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.productscounter.dto.GroceryList;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.profile.domain.UserFacade;
import pl.mealplanner.profile.domain.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@Component
public class ListOfProductsForPlan {
    private final ProductsCounterService productsCounterService;
    private final UserFacade userFacade;
    private static final Set<PlanProductInfo> productsForPlan = new HashSet<>();

    public void clearListOfProductsForPlan() {
        productsForPlan.clear();
    }

    public Set<PlanProductInfo> getListOfProductsForPlan() {
        return productsForPlan;
    }

    public List<GroceryList> mapToGroceryList() {
        return productsForPlan.stream()
                .map(p -> GroceryList.builder()
                        .name(p.getName())
                        .packingMeasure(p.getPackingMeasure())
                        .nrOfPackets(p.getNrOfPackets())
                        .unit(p.getUnitCount())
                        .build())
                .toList();
    }

    public List<GroceryList> getCurrentGroceryList() {
        User currentUser = userFacade.getCurrentUser();
        return currentUser.getGroceryList();
    }

    public void addAll(List<PlanProductInfo> productsToUse) {
        productsToUse.forEach(this::add);
    }

    public void add(PlanProductInfo productToAdd) {
        if (productsForPlan.isEmpty()) {
            productsForPlan.add(productToAdd);
        } else {
            addAndUpdatePacking(productToAdd);
        }
    }

    private void addAndUpdatePacking(PlanProductInfo pToAdd) {
        int i = 0;
        for (PlanProductInfo pInList : productsForPlan) {
            if (pInList.equals(pToAdd)) {
                if (pToAdd.getAmountToUseCount() <= pInList.getSurplus()) {
                    // odejmuje z surplus, dodaje w amount
                    setNewAmountAndSurplus(pToAdd, pInList);

                } else {
                    // jeśli pToAdd.amount > pInList.surplus to dobieranie od nowa opakowania
                    float ingAmount = pInList.getAmountToUseCount() + pToAdd.getAmountToUseCount();
                    PlanProductInfo updatedProduct = productsCounterService.calculateForOneProduct(pToAdd.getName(), ingAmount);
                    replaceProduct(pInList, updatedProduct);
                }
                break;
            }
            i++;
        }
        if (i == productsForPlan.size()) {
            productsForPlan.add(pToAdd);
        }
    }

    private void setNewAmountAndSurplus(PlanProductInfo pToAdd, PlanProductInfo pInList) {
        pInList.setAmountToUseCount(pInList.getAmountToUseCount() + pToAdd.getAmountToUseCount());
        pInList.setSurplus(pInList.getSurplus() - pToAdd.getAmountToUseCount());
    }

    private void replaceProduct(PlanProductInfo pInList, PlanProductInfo pForReplacement) {
        if (pInList.equals(pForReplacement)) {
            pInList.setAmountToUseCount(pForReplacement.getAmountToUseCount());
            pInList.setPackingMeasure(pForReplacement.getPackingMeasure());
            pInList.setNrOfPackets(pForReplacement.getNrOfPackets());
            pInList.setSurplus(pForReplacement.getSurplus());
        }
    }
}
