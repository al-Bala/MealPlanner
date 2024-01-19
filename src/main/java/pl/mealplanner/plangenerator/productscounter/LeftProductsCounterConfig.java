//package pl.mealplanner.plangenerator.leftproductscounter;
//
//import org.springframework.context.annotation.Configuration;
//import pl.mealplanner.plangenerator.packingchooser.PackingChooserConfig;
//
//@Configuration
//public class LeftProductsCounterConfig {
//    public LeftProductsCounterFacade createForTest(LeftProductsCounterRepository repository){
//        PackingChooserConfig packingChooserConfig = new PackingChooserConfig();
//        LeftProductsCounterService leftProductsCounterService = new LeftProductsCounterService(packingChooserConfig.createForTest(), repository);
////        GroceryList groceryList = new GroceryList(leftProductsCounterService);
//        return new LeftProductsCounterFacade(leftProductsCounterService);
//    }
//}
