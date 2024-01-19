package pl.mealplanner.profile.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.mealplanner.plangenerator.productscounter.dto.GroceryList;
import pl.mealplanner.profile.domain.entity.PlanHistory;
import pl.mealplanner.profile.domain.entity.User;

import java.util.List;

@AllArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser(){
        String username = authenticate();
        return getUserByUsername(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public User updateUserPlanAndGroceryList(String username, List<PlanHistory> plan, List<GroceryList> groceryList) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            userRepository.deleteByUsername(user.getUsername());
            user.setPlanHistory(plan);
            user.setGroceryList(groceryList);
            return userRepository.save(user);
        }
        return null;
    }

    public String authenticate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
