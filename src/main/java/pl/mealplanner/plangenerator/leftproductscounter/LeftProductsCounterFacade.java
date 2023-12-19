package pl.mealplanner.plangenerator.leftproductscounter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.grocerylistmaker.GroceryList;
import pl.mealplanner.plangenerator.leftproductscounter.dto.IngredientsToUseInfo;
import pl.mealplanner.plangenerator.leftproductscounter.dto.Leftover;
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

    public List<Leftover> calculateLeftovers(FilteredRecipeDto recipe){
        return recipe.ingredients().stream()
                .map(ingredient -> calculateLeftovers(getProduct(ingredient), ingredient.amount()))
                .filter(leftover -> leftover.surplus() != 0f)
                .toList();

//        List<IngredientDto> theRests = leftovers.stream()
//                .map(f -> new IngredientDto(f.name(), f.surplus(), f.unit()))
//                .toList();
    }

    private Product getProduct(IngredientDto ingredient){
        return repository.findByName(ingredient.name());
    }

    public Leftover calculateLeftovers(Product product, float recipeAmount){
        IngredientsToUseInfo ing = packingChooserFacade.choosePacking(product, recipeAmount);
        return LeftoverMapper.mapFromIngredientsToUseInfoToLeftover(ing);
    }
}
