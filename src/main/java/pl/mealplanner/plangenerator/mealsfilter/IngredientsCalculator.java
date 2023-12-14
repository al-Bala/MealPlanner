package pl.mealplanner.plangenerator.mealsfilter;

import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.ProductDto;

import java.util.List;

@Component
class IngredientsCalculator {

    public FilteredRecipeDto calculateIngredients(FilteredRecipeDto filteredRecipeDto, int nrPortionsUser) {
        int nrPortionsRecipe = filteredRecipeDto.portions();

        if (nrPortionsRecipe == nrPortionsUser) {
            return filteredRecipeDto;
        } else {
            List<ProductDto> productsWithCalculatedAmounts = filteredRecipeDto.ingredients().stream()
                    .map(product -> calculateProductDto(product, nrPortionsRecipe, nrPortionsUser))
                    .toList();

            return filteredRecipeDto.toBuilder()
                    .portions(nrPortionsUser)
                    .ingredients(productsWithCalculatedAmounts)
                    .build();
        }
    }

    private ProductDto calculateProductDto(ProductDto product, int nrPortionsRecipe, int nrPortionsUser) {
        return ProductDto.builder()
                .name(product.name())
                .amount(product.amount() * nrPortionsUser / nrPortionsRecipe)
                .unit(product.unit())
                .build();
    }
}
