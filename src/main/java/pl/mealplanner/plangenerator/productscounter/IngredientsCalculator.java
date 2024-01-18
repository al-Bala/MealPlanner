package pl.mealplanner.plangenerator.productscounter;

import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientConverted;
import pl.mealplanner.plangenerator.unitconverter.DisplayCountAU;

import java.util.List;

@Component
class IngredientsCalculator {

    public ConvertedRecipe calculateIngredients(ConvertedRecipe convertedRecipe, int nrPortionsUser, int forHowManyDays) {
        int nrPortionsRecipe = convertedRecipe.portions();

        if (nrPortionsRecipe == nrPortionsUser) {
            return convertedRecipe;
        } else {
            List<IngredientConverted> ingWithCalculatedAmounts = convertedRecipe.ingredients().stream()
                    .map(ing -> calculateProductDto(ing, nrPortionsRecipe, nrPortionsUser, forHowManyDays))
                    .toList();

            return convertedRecipe.toBuilder()
                    .portions(nrPortionsUser)
                    .ingredients(ingWithCalculatedAmounts)
                    .build();
        }
    }

    private IngredientConverted calculateProductDto(IngredientConverted ing, int nrPortionsRecipe, int nrPortionsUser, int forHowManyDays) {
        DisplayCountAU au = ing.amountsAndUnit();
        DisplayCountAU calculatedAmountsWithUnits = new DisplayCountAU(
                au.getAmountDisplay() * nrPortionsUser / nrPortionsRecipe * forHowManyDays,
                au.getUnitDisplay(),
                au.getAmountCount() * nrPortionsUser / nrPortionsRecipe * forHowManyDays,
                au.getUnitCount()
        );

        return IngredientConverted.builder()
                .name(ing.name())
                .amountsAndUnit(calculatedAmountsWithUnits)
                .build();
    }
}
