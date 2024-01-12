package pl.mealplanner.plangenerator.packingchooser;

import org.junit.Assert;
import org.junit.Test;
import pl.mealplanner.plangenerator.leftproductscounter.entity.PackingMeasures;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.List;

public class PackingChooserFacadeTest {

//    @Test
//    public void test(){
//        // given
//        PackingChooserFacade facade = new PackingChooserFacade(new PackingChooserService());
//
//        Product product = Product.builder().build();
////                new Product("marchew", List.of(
////                new PackingMeasures(0f, "g"),
////                new PackingMeasures(0f, "szt")
////        ));
//
//        IngredientDto ing = new IngredientDto("marchew", 200f, "g");
//
//        // when
//        IngredientToUseInfo ingInfo = facade.choosePacking(product, ing);
//        System.out.println(ingInfo);
//
//        // then
//        IngredientToUseInfo expected = new IngredientToUseInfo("marchew", 200f, 0, 0,"g");
//        Assert.assertEquals(expected, ingInfo);
//    }
//
//    @Test
//    public void test2(){
//        // given
//        PackingChooserFacade facade = new PackingChooserFacade(new PackingChooserService());
//
//        Product product = Product.builder().build();
////        Product product = new Product("marchew", List.of(
////                new PackingMeasures(0f, "g"),
////                new PackingMeasures(0f, "szt")
////        ));
//
//        IngredientDto ing = new IngredientDto("marchew", 5f, "szt");
//
//        // when
//        IngredientToUseInfo ingInfo = facade.choosePacking(product, ing);
//        System.out.println(ingInfo);
//
//        // then
//        IngredientToUseInfo expected = new IngredientToUseInfo("marchew", 5f, 0, 0,"szt");
//        Assert.assertEquals(expected, ingInfo);
//    }
//
//    @Test
//    public void test3(){
//        // given
//        PackingChooserFacade facade = new PackingChooserFacade(new PackingChooserService());
//
//        Product product = Product.builder().build();
////        Product product = new Product("kasza", List.of(
////                new PackingMeasures(180f, "g"),
////                new PackingMeasures(450f, "g"),
////                new PackingMeasures(600f, "g")
////        ));
//
//        IngredientDto ing = new IngredientDto("kasza", 500f, "g");
//
//        // when
//        IngredientToUseInfo ingInfo = facade.choosePacking(product, ing);
//        System.out.println(ingInfo);
//
//        // then
//        IngredientToUseInfo expected = new IngredientToUseInfo("kasza", 180f, 3, 0,"g");
//        Assert.assertEquals(expected, ingInfo);
//    }
//
//    @Test
//    public void test4(){
//        // given
//        PackingChooserFacade facade = new PackingChooserFacade(new PackingChooserService());
//
//        Product product = Product.builder().build();
////        Product product = new Product("kasza", List.of(
////                new PackingMeasures(180f, "g"),
////                new PackingMeasures(450f, "g"),
////                new PackingMeasures(600f, "g")
////        ));
//
//        IngredientDto ing = new IngredientDto("kasza", 300f, "g");
//
//        // when
//        IngredientToUseInfo ingInfo = facade.choosePacking(product, ing);
//        System.out.println(ingInfo);
//
//        // then
//        IngredientToUseInfo expected = new IngredientToUseInfo("kasza", 450f, 1, 150,"g");
//        Assert.assertEquals(expected, ingInfo);
//    }

}