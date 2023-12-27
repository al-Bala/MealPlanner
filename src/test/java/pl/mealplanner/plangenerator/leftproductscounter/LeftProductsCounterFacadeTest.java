package pl.mealplanner.plangenerator.leftproductscounter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import pl.mealplanner.plangenerator.leftproductscounter.dto.Leftover;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;
import pl.mealplanner.plangenerator.packingchooser.PackingChooserConfig;

import java.util.Collections;
import java.util.List;

public class LeftProductsCounterFacadeTest {
    PackingChooserConfig packingChooserConfig = new PackingChooserConfig();
    @Test
    public void test(){
        // given
        LeftProductsCounterFacade facade = new LeftProductsCounterFacade(packingChooserConfig.createForTest(), new LeftProductsCounterRepositoryTestImpl() ,new GroceryList());
        FilteredRecipeDto recipe = FilteredRecipeDto.builder()
                .ingredients(List.of(
                        new IngredientDto("kasza", 300f, "g")
                ))
                .build();
        // when
        List<Leftover> result = facade.calculateProducts(recipe);
        System.out.println(result);

        // then
        List<Leftover> expected = List.of(new Leftover("kasza", 150f, "g"));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test2(){
        // given
        LeftProductsCounterFacade facade = new LeftProductsCounterFacade(packingChooserConfig.createForTest(), new LeftProductsCounterRepositoryTestImpl() ,new GroceryList());
        FilteredRecipeDto recipe = FilteredRecipeDto.builder()
                .ingredients(List.of(
                        new IngredientDto("kasza", 500f, "g")
                ))
                .build();
        // when
        List<Leftover> result = facade.calculateProducts(recipe);
        System.out.println(result);

        // then
        List<Leftover> expected = Collections.emptyList();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test3(){
        // given
        LeftProductsCounterFacade facade = new LeftProductsCounterFacade(packingChooserConfig.createForTest(), new LeftProductsCounterRepositoryTestImpl() ,new GroceryList());
        FilteredRecipeDto recipe = FilteredRecipeDto.builder()
                .ingredients(List.of(
                        new IngredientDto("marchew", 2f, "szt"),
                        new IngredientDto("marchew", 100f, "g")
                ))
                .build();
        // when
        List<Leftover> result = facade.calculateProducts(recipe);
        System.out.println(result);

        // then
        List<Leftover> expected = Collections.emptyList();
        Assert.assertEquals(expected, result);
    }
}