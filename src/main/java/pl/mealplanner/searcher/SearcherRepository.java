package pl.mealplanner.searcher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mealplanner.displayer.Recipes;

import java.util.List;

@Repository
public interface SearcherRepository extends MongoRepository<Recipes, String> {
    List<Recipes> findByNameContaining(String keyword);

    List<Recipes> findByNameContainingIgnoreCase(String keyword);

}

