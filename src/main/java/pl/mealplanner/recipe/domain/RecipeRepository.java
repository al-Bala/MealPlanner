package pl.mealplanner.recipe.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

//    // to można zmienić na inny sposób wyszukiwania
//    @Query("{diet: ?0, prepare_time: { $lt: ?1 }, max_storage_time: { $lt: ?2 }}")
//    Optional<List<Recipe>> findByDietAndPrepareTimeAndStorageTime(String diet, int prepareTimeMin, int maxStorageTimeDays);
//
//    @Query("{prepare_time: { $lt: ?0 }, max_storage_time: { $lt: ?1 }}")
//    Optional<List<Recipe>> findByPrepareTimeAndStorageTime(int prepareTimeMin, int maxStorageTimeDays);
//
//    @Query("{ ingredients: { $regex: ?0, $options: 'i' } }")
//    Optional<Recipe> findByIngredientsContaining(String name);

    Optional<Recipe> findById(ObjectId recipeId);
}
