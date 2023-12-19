package pl.mealplanner.plangenerator.mealsfilter;

import org.junit.Assert;
import org.junit.Test;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.List;

public class IngredientsCalculatorTest {

    @Test
    public void test(){
        // given
        IngredientsCalculator calculator = new IngredientsCalculator();

        FilteredRecipeDto filteredRecipeDto = FilteredRecipeDto.builder()
                .name("name")
                .portions(2)
                .ingredients(List.of(
                        new IngredientDto("product1", 100, "ml"),
                        new IngredientDto("product2", 125, "g")
                ))
                .build();

        // when
        FilteredRecipeDto convertedRecipeDto = calculator.calculateIngredients(filteredRecipeDto, 3);

        // then
        FilteredRecipeDto expectedFilteredRecipeDto = FilteredRecipeDto.builder()
                .name("name")
                .portions(3)
                .ingredients(List.of(
                        new IngredientDto("product1", 150, "ml"),
                        new IngredientDto("product2", 187.5f, "g")
                        ))
                .build();

        Assert.assertEquals(expectedFilteredRecipeDto, convertedRecipeDto);
    }

}