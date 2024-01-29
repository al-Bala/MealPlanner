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
    private static final Set<PlanProductInfo> productsFromUser = new HashSet<>();

    public void clearListOfProductsForPlan() {
        productsForPlan.clear();
        productsFromUser.clear();
    }

    public Set<PlanProductInfo> getListOfProductsForPlan() {
        return productsForPlan;
    }

    public List<GroceryList> mapToGroceryList() {
        return productsForPlan.stream()
                .filter(p -> p.getNrOfPackets() != 0)
                .map(p -> GroceryList.builder()
                        .name(p.getName())
                        .amount(Math.round(p.getAmountToUseCount() * 10.0) / 10.0f)
                        .unit(p.getUnitCount())
                        .build())
                .toList();
    }

    public List<GroceryList> getCurrentGroceryList() {
        User currentUser = userFacade.getCurrentUser();
        return currentUser.getGroceryList();
    }

    public void addAllForPlan(List<PlanProductInfo> productsToUse) {
        productsForPlan.addAll(productsToUse);
    }

    public void addAllFromUser(List<PlanProductInfo> productsToUse) {
        productsFromUser.addAll(productsToUse);
    }

    public void updateAfterChoseRecipe(PlanProductInfo productToAdd) {
        if (!productsFromUser.isEmpty()) {
            for (PlanProductInfo pInListUser : productsFromUser) {
                if (pInListUser.equals(productToAdd)) {
                    float amount = productToAdd.getAmountToUseCount() - pInListUser.getSurplus();
                    float surplus = pInListUser.getSurplus() - productToAdd.getAmountToUseCount();
                    float amount1 = amount > 0 ? amount : 0;
                    float surplus1 = surplus > 0 ? surplus : 0;

                    productToAdd.setAmountToUseCount(amount1);
                    pInListUser.setSurplus(surplus1);
                }
            }
            productsFromUser.removeIf(s -> s.getSurplus() == 0);
        }
    }

    public void addToPlan(PlanProductInfo productToAdd) {
        if (productsForPlan.isEmpty()) {
            productsForPlan.add(productToAdd);
        } else {
            addToPlanAndUpdatePacking(productToAdd);
        }
    }

    private void addToPlanAndUpdatePacking(PlanProductInfo pToAdd) {
        int i = 0;
        for (PlanProductInfo pInList : productsForPlan) {
            if (pInList.equals(pToAdd)) {
                if (pToAdd.getAmountToUseCount() <= pInList.getSurplus()) {
                    // odejmuje z surplus, dodaje w amount
                    setNewAmountAndSurplus(pToAdd, pInList);

                } else {
                    // jeśli pToAdd.amount > pInList.surplus to dobieranie od nowa opakowania
                    float ingAmount = pInList.getAmountToUseCount() + (pToAdd.getAmountToUseCount() - pInList.getSurplus());
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
