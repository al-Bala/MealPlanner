package pl.mealplanner.profile.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mealplanner.loginandregister.domain.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Dodatkowe metody repozytorium, jeśli są potrzebne

//    User findByEmail(String email);
    Optional<User> findByUsername(String username);
}
