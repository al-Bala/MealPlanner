package pl.mealplanner.profile.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;
import pl.mealplanner.profile.domain.entity.User;
import pl.mealplanner.recipe.domain.RecipeRepository;
import pl.mealplanner.recipe.domain.entity.RecipeClass;

import java.util.Optional;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    public Optional<User> findUserById(String id) {
//        return userRepository.findById(id);
//    }


//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean toggleFavorite(String username, String recipeId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> favorites = user.getFavorites();
        boolean isInFavorite;

        if (favorites.contains(recipeId)) {
            favorites.remove(recipeId);
            isInFavorite = false;

        } else {
            favorites.add(recipeId);
            isInFavorite = true;
        }

        // Zapisz zmiany w użytkowniku, jeśli to konieczne
        userRepository.save(user);
        return isInFavorite;
    }

    public boolean isFavorite(String username, String recipeId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> favorites = user.getFavorites();
        return favorites.contains(recipeId);
    }


    public List<RecipeClass> getFavoriteRecipes(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            return user.getFavorites().stream()
                    .map(recipeRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
