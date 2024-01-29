package pl.mealplanner.plangenerator.unitconverter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.productscounter.ListOfProductsForPlan;
import pl.mealplanner.plangenerator.mealsfilter.dto.ConvertedRecipe;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientConverted;
import pl.mealplanner.plangenerator.domain.dto.IngredientDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.MatchingRecipe;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;

import java.util.List;

@AllArgsConstructor
@Component
public class UnitConverterFacade {

    private final UnitConverterService unitConverterService;
    private final ListOfProductsForPlan listOfProductsForPlan;

    public void includeUserProductsToUse(List<IngredientDto> productsToUse) {
        List<IngredientConverted> convertedProducts = convertProductsToUseToMainUnit(productsToUse);
        List<PlanProductInfo> productsToUseList = UnitMapper.mapForIncludeUserProductsToUse(convertedProducts);
        listOfProductsForPlan.addAllFromUser(productsToUseList);
        listOfProductsForPlan.addAllForPlan(productsToUseList);
    }

    public ConvertedRecipe convertIngsFromRecipeToMainUnit(MatchingRecipe recipe) {
        List<IngredientDto> ingredients = recipe.ingredients();
        List<IngredientConverted> convertedIngList = unitConverterService.convertAllToMainUnit(ingredients, "display");
        return ConvertedRecipe.builder()
                .id(recipe.id())
                .name(recipe.name())
                .portions(recipe.portions())
                .prepare_time(recipe.prepare_time())
                .diet(recipe.diet())
                .ingredients(convertedIngList)
                .steps(recipe.steps())
                .build();
    }

    private List<IngredientConverted> convertProductsToUseToMainUnit(List<IngredientDto> ingredients) {
        return unitConverterService.convertAllToMainUnit(ingredients, "withoutDisplay");
    }
}
