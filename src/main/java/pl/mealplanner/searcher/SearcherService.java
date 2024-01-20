package pl.mealplanner.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mealplanner.displayer.Recipes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearcherService {

    @Autowired
    private SearcherRepository searcherRepository;
    public List<Recipes> getRandomRecipes() {
        List<Recipes> allRecipes = searcherRepository.findAll();
        Collections.shuffle(allRecipes);
        return allRecipes.subList(0, Math.min(3, allRecipes.size()));
    }

    public List<Recipes> getRandomRecipesByDiet(String diet) {
        List<Recipes> dietRecipes = searcherRepository.findByDiet(diet);
        Collections.shuffle(dietRecipes);
        return dietRecipes.subList(0, Math.min(3, dietRecipes.size()));
    }

    public List<String> getAvailableDiets() {
        List<Recipes> recipesWithDiet = searcherRepository.findAllDiets();
        return recipesWithDiet.stream()
                .map(Recipes::getDiet)
                .distinct()
                .collect(Collectors.toList());
    }

    public Optional<Recipes> findRecipeById(String id) {
        return searcherRepository.findById(id);
    }

}
