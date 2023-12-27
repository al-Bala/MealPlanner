package pl.mealplanner.displayer;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface RecipesRepository extends MongoRepository<Recipes, String> {

    List<Recipes> findByDiet(String diet);



}