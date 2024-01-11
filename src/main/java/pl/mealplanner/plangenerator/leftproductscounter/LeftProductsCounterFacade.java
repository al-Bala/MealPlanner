package pl.mealplanner.plangenerator.leftproductscounter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientToUseInfo;
import pl.mealplanner.plangenerator.leftproductscounter.dto.Leftover;
import pl.mealplanner.plangenerator.leftproductscounter.dto.ShoppingInfo;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;
import pl.mealplanner.plangenerator.packingchooser.PackingChooserFacade;

import java.util.List;

@AllArgsConstructor
@Component
public class LeftProductsCounterFacade {

    private final PackingChooserFacade packingChooserFacade;
    private final LeftProductsCounterRepository repository;
    private final GroceryList groceryList;

    public List<Leftover> calculateProducts(FilteredRecipeDto recipe){

        List<IngredientToUseInfo> IngredientsToUseList = recipe.ingredients().stream()
                .map(ingredient -> calculatePacketAndLeftovers(getProduct(ingredient), ingredient))
                .toList();

        IngredientsToUseList.forEach(this::addToGroceryList);

        return IngredientsToUseList.stream()
                .map(LeftoverMapper::mapFromIngredientsToUseInfoToLeftover)
                .filter(leftover -> leftover.surplus() != 0f)
                .toList();
    }

    public Product findProduct(String name){
        return repository.findByName(name)
                .orElseThrow();
    }

    private Product getProduct(IngredientDto ingredient){
        return repository.findByName(ingredient.name())
                .orElseThrow();
    }

    private IngredientToUseInfo calculatePacketAndLeftovers(Product product, IngredientDto ingRecipe){
        return packingChooserFacade.choosePacking(product, ingRecipe);
    }
    
    private void addToGroceryList(IngredientToUseInfo ing){
        ShoppingInfo productToBuy = LeftoverMapper.mapFromIngredientsToUseInfoToShoppingInfo(ing);
        groceryList.addProductToGroceryList(productToBuy);
    }
}
