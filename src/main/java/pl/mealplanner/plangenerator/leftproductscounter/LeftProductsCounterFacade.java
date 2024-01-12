package pl.mealplanner.plangenerator.leftproductscounter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.List;

@AllArgsConstructor
@Component
public class LeftProductsCounterFacade {

    private final LeftProductsCounterService leftProductsCounterService;

    public List<PlanProductInfo> calculateProducts(FilteredRecipeDto recipe){

        return recipe.ingredients().stream()
                .map(leftProductsCounterService::calculatePacketAndLeftovers)
                .filter(leftover -> leftover.getSurplus() != 0f)
                .toList();
    }

    public Product findProductByName(String name){
        return leftProductsCounterService.findProduct(name);
    }

}
