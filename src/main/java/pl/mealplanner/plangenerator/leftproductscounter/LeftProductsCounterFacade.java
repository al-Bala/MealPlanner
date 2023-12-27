package pl.mealplanner.plangenerator.leftproductscounter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientsToUseInfo;
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

        List<IngredientsToUseInfo> IngredientsToUseList = recipe.ingredients().stream()
                .map(ingredient -> calculatePacketAndLeftovers(getProduct(ingredient), ingredient.amount()))
                .toList();

        IngredientsToUseList.forEach(this::addToGroceryList);

        return IngredientsToUseList.stream()
                .map(LeftoverMapper::mapFromIngredientsToUseInfoToLeftover)
                .filter(leftover -> leftover.surplus() != 0f)
                .toList();
    }

    private Product getProduct(IngredientDto ingredient){
        return repository.findByName(ingredient.name())
                .orElseThrow();
    }

    private IngredientsToUseInfo calculatePacketAndLeftovers(Product product, float recipeAmount){
        return packingChooserFacade.choosePacking(product, recipeAmount);
    }
    
    private void addToGroceryList(IngredientsToUseInfo ing){
        ShoppingInfo productToBuy = LeftoverMapper.mapFromIngredientsToUseInfoToShoppingInfo(ing);
        groceryList.addProductToGroceryList(productToBuy);
    }
}
