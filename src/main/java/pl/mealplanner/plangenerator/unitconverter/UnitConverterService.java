package pl.mealplanner.plangenerator.unitconverter;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.productscounter.ProductsCounterFacade;
import pl.mealplanner.plangenerator.productscounter.entity.Product;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientConverted;
import pl.mealplanner.plangenerator.domain.dto.IngredientDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Log4j2
@Component
class UnitConverterService {
    private final ProductsCounterFacade productsCounterFacade;

    public List<IngredientConverted> convertAllToMainUnit(List<IngredientDto> ingredients, String description) {
        List<IngredientConverted> convertedIngList = new ArrayList<>();
        for (IngredientDto ing : ingredients
        ) {
            Product product = productsCounterFacade.findProductByName(ing.name());
            IngredientConverted convertedIng = convertUnit(product, ing, description);
            convertedIngList.add(convertedIng);
        }
        return convertedIngList;
    }

    private IngredientConverted convertUnit(Product product, IngredientDto ingRecipe, String description) {
        DisplayCountAU au = getAmountAndUnitClass(product, ingRecipe);
        switch (description) {
            case "withoutDisplay": {
                au.setAmountDisplay(au.getAmountCount());
                au.setUnitDisplay(au.getUnitCount());
            }
            case "display": {
                au.setAmountDisplay(ingRecipe.amount());
                au.setUnitDisplay(ingRecipe.unit());
            }
        }
        return IngredientConverted.builder()
                .name(ingRecipe.name())
                .amountsAndUnit(au)
                .build();
    }

    private DisplayCountAU getAmountAndUnitClass(Product product, IngredientDto ingRecipe) {
        DisplayCountAU au = new DisplayCountAU();
        if (ingRecipe.unit().equals(product.mainUnit())) {
            au.setAmountCount(ingRecipe.amount());
            au.setUnitCount(ingRecipe.unit());
        } else {
            AmountAndUnit countedInfo = count(product, ingRecipe);
            au.setAmountCount(countedInfo.amount());
            au.setUnitCount(countedInfo.unit());
        }
        return au;
    }

    private AmountAndUnit count(Product product, IngredientDto ingRecipe) {
        switch (ingRecipe.unit()) {
            case "kg", "l":
                return AmountAndUnit.builder()
                        .amount(ingRecipe.amount() * 1000)
                        .unit(product.mainUnit())
                        .build();
            case "łyżeczka":
                return AmountAndUnit.builder()
                        .amount(ingRecipe.amount() * 5)
                        .unit("g")
                        .build();
            case "łyżka":
                return AmountAndUnit.builder()
                        .amount(ingRecipe.amount() * 15)
                        .unit("g")
                        .build();
            case "szt": {
                if (product.packingMeasures().size() == 1) {
                    return AmountAndUnit.builder()
                            .amount(ingRecipe.amount() * product.packingMeasures().get(0))
                            .unit(product.mainUnit())
                            .build();
                }
                log.error("Brak jednoznacznie zdefiniowanej wagi dla szt produktu: " + product.name());
                return null;
            }
        }
        log.error("Błąd przy przeliczaniu jedonstek w przepisie dla składnika: " + ingRecipe.name());
        return null;
    }
}

