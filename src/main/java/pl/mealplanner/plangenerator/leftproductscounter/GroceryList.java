package pl.mealplanner.plangenerator.leftproductscounter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.ShoppingInfo;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Component
public class GroceryList {

    private static final List<ShoppingInfo> groceryList = new ArrayList<>();

    public void addProductToGroceryList(ShoppingInfo product){
        groceryList.add(product);
    }

    public void clearGroceryList(){
        groceryList.clear();
    }

    public List<ShoppingInfo> getGroceryList(){
        return groceryList;
    }
}
