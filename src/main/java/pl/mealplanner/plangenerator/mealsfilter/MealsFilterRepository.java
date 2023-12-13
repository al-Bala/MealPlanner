package pl.mealplanner.plangenerator.mealsfilter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.List;

@Repository
interface MealsFilterRepository extends MongoRepository<Recipe, String>{
    @Query("{'max_storage_time': { $gte: ?0 }, 'diet': { $eq: ?1 }, 'prepare_time': { $lte: ?2 }, 'ingredients.name': { $all: ?3, $nin: ?4 }}")
    List<Recipe> findMatchingRecipes(int maxStorageTime, String diets, int prepareTime, List<String> productsToUse, List<String> dislikedProducts);
}
