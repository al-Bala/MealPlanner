package pl.mealplanner.searcher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mealplanner.displayer.Recipes;

import java.util.List;

@Repository
public interface SearcherRepository extends MongoRepository<Recipes, String> {
    List<Recipes> findByDiet(String diet);

    @Query(value = "{}", fields = "{diet : 1}")
    List<Recipes> findAllDiets();

}

