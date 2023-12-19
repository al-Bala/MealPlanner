package pl.mealplanner.plangenerator.leftproductscounter;

import org.junit.Assert;
import org.junit.Test;
import pl.mealplanner.plangenerator.leftproductscounter.dto.Leftover;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;
import pl.mealplanner.plangenerator.packingchooser.PackingChooserConfig;

import java.util.List;

public class LeftProductsPackingChooserServiceFacadeTest {
    PackingChooserConfig packingChooserConfig = new PackingChooserConfig();

    @Test
    public void test(){
        // given
        LeftProductsCounterFacade facade = new LeftProductsCounterFacade(packingChooserConfig.createForTest(), new LeftProductsCounterRepositoryTestImpl());
        FilteredRecipeDto recipe = FilteredRecipeDto.builder()
                .ingredients(List.of(
//                        new IngredientDto("mleko", 150f, "ml"),
                        new IngredientDto("kasza", 500f, "g")
//                        new IngredientDto("jogurt", 400f, "g")
                ))
                .build();
        // when
        List<Leftover> result = facade.calculateLeftovers(recipe);
        System.out.println(result);

        // then
        List<Leftover> expected = List.of(new Leftover("kasza", 0f, "g"));
//        List<ForCounting> expected = List.of(new ForCounting(180f, 2, 10));
        Assert.assertEquals(expected, result);
    }
}