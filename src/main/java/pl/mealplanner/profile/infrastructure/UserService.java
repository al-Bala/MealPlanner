package pl.mealplanner.profile.infrastructure;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mealplanner.loginandregister.domain.entity.User;
import pl.mealplanner.profile.domain.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

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
}
