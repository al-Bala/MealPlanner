package pl.mealplanner.plangenerator.leftproductscounter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;
import pl.mealplanner.plangenerator.packingchooser.PackingChooserFacade;

@AllArgsConstructor
@Component
class LeftProductsCounterService {

    private final PackingChooserFacade packingChooserFacade;
    private final LeftProductsCounterRepository repository;

    public PlanProductInfo calculatePacketAndLeftovers(IngredientDto ing){
        Product product = findProduct(ing.name());
        return packingChooserFacade.choosePacking(product, ing);
    }

    public Product findProduct(String name){
        return repository.findByName(name)
                .orElseThrow();
    }
}

