//package pl.mealplanner.plangenerator.mealsfilter;
//
//import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
//import pl.mealplanner.plangenerator.mealsfilter.entity.Ingredient;
//import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;
//
//import java.util.List;
//
//public class MealsFilterRepositoryTestImpl implements MealsFilterRepository {
//
////    private final Map<String, Product> products = new HashMap<>();
////
////    MealsFilterRepositoryTestImpl(){
////        products.put("mleko", new Product("mleko", List.of( "ml", "l"), "ml", List.of(500)));
////        products.put("kasza", new Product("kasza", List.of( "g", "kg"), "g", List.of(180,450,600)));
////        products.put("jogurt", new Product("jogurt", List.of( "g", "kg"), "g", List.of(150,250)));
////        products.put("marchew", new Product("marchew", List.of( "g", "kg", "szt"), "g", List.of(100)));
////    }
//
//    @Override
//    public List<Recipe> findMatchingRecipes(InfoForFiltering info) {
//        return List.of(
//                Recipe.builder()
//                        .name("Kasza jaglana z warzywami")
//                        .portions(4)
//                        .prepareTimeMin(30)
//                        .maxStorageTimeDays(2)
//                        .diet("wegetariańska")
//                        .ingredients(List.of(
//                                new Ingredient("kasza jaglana", 200, "g"),
//                                new Ingredient("marchew", 2, "szt")
//                        ))
//                        .build()
//        );
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
//    @Override
//    public String toString() {
//        return super.toString();
//    }
//}
