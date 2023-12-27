package pl.mealplanner.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mealplanner.displayer.Recipes;

import java.util.List;

@Service
public class SearcherService {

    @Autowired
    private SearcherRepository repository;
    public List<Recipes> searchByName(String keyword) {
        return repository.findByNameContaining(keyword);
    }

    public List<Recipes> getSuggestions(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Recipes> getAllRecipes() {
        return repository.findAll();
    }

    public List<Recipes> nullowanie() {
        return null;
    }
}
