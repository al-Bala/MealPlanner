package pl.mealplanner.plangenerator.productscounter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.productscounter.entity.Product;

import java.util.List;

@AllArgsConstructor
@Component
public class ProductsCounterFacade {

    private final ProductsCounterService productsCounterService;
    private final IngredientsCalculator ingredientsCalculator;

    public List<PlanProductInfo> choosePacketsAndCountLeftovers(List<PlanProductInfo> ingredients){
        return ingredients.stream()
                .map(ing -> productsCounterService.calculateForOneProduct(ing.getName(), ing.getAmountToUseCount()))
                .toList();
    }

    public Product findProductByName(String name){
        return productsCounterService.findProduct(name);
    }

    public ConvertedRecipe calculateIngredients(ConvertedRecipe convertedRecipe, int nrPortionsUser, int forHowManyDays) {
        return ingredientsCalculator.calculateIngredients(convertedRecipe, nrPortionsUser ,forHowManyDays);
    }

}
