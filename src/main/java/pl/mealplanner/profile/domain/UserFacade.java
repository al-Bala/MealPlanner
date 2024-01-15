package pl.mealplanner.profile.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.mealplanner.loginandregister.domain.dto.PlanHistoryDto;
import pl.mealplanner.loginandregister.domain.entity.PlanHistory;

import java.util.List;

@AllArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<PlanHistoryDto> findPlanHistoryByCurrentUser() {
        String username = authenticate();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return UserMapper.mapFromUserToPlanHistoryList(user);
    }

    @Transactional
    public User updateUserPlanHistory(String username, List<PlanHistory> plan) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            userRepository.deleteByUsername(user.getUsername());
            user.setPlanHistory(plan);
            return userRepository.save(user);
        }
        return null;
    }

    public String authenticate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
