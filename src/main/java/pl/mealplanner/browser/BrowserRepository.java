package pl.mealplanner.browser;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mealplanner.displayer.Recipes;

import java.util.List;
public interface BrowserRepository extends MongoRepository<Recipes, String> {
    List<Recipes> findByDiet(String diet);
}
