package pl.mealplanner.plangenerator.leftproductscounter;

import org.junit.Test;
import pl.mealplanner.plangenerator.leftproductscounter.dto.PlanProductInfo;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;
import pl.mealplanner.plangenerator.packingchooser.PackingChooserConfig;

import java.util.List;

public class LeftProductsCounterFacadeTest {
    PackingChooserConfig packingChooserConfig = new PackingChooserConfig();
    @Test
    public void chose_packet_test_1(){
        // given
        LeftProductsCounterFacade facade = new LeftProductsCounterFacade(
                new LeftProductsCounterService(packingChooserConfig.createForTest(), new LeftProductsCounterRepositoryTestImpl()));

        FilteredRecipeDto recipe = FilteredRecipeDto.builder()
                .ingredients(List.of(
                        new IngredientDto("kasza", 300f, "g")
                ))
                .build();
        // when
        List<PlanProductInfo> result = facade.calculateProducts(recipe);
        System.out.println(result);

        // then
//        List<Leftover> expected = List.of(new Leftover("kasza", 150f, "g"));
//        Assert.assertEquals(expected, result);
    }

    @Test
    public void chose_packet_test_2(){
        // given
        LeftProductsCounterFacade facade = new LeftProductsCounterFacade(
                new LeftProductsCounterService(packingChooserConfig.createForTest(), new LeftProductsCounterRepositoryTestImpl()));

        FilteredRecipeDto recipe = FilteredRecipeDto.builder()
                .ingredients(List.of(
                        new IngredientDto("kasza", 500f, "g")
                ))
                .build();
        // when
        List<PlanProductInfo> result = facade.calculateProducts(recipe);
        System.out.println(result);

        // then
//        List<Leftover> expected = Collections.emptyList();
//        Assert.assertEquals(expected, result);
    }

    @Test
    public void chose_packet_test_3(){
        // given
        LeftProductsCounterFacade facade = new LeftProductsCounterFacade(
                new LeftProductsCounterService(packingChooserConfig.createForTest(), new LeftProductsCounterRepositoryTestImpl()));

        FilteredRecipeDto recipe = FilteredRecipeDto.builder()
                .ingredients(List.of(
                        new IngredientDto("marchew", 2f, "szt"),
                        new IngredientDto("marchew", 100f, "g")
                ))
                .build();
        // when
        List<PlanProductInfo> result = facade.calculateProducts(recipe);
        System.out.println(result);

        // then
//        List<Leftover> expected = Collections.emptyList();
//        Assert.assertEquals(expected, result);
    }
}