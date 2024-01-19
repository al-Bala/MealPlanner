package pl.mealplanner.loginandregister.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mealplanner.profile.domain.entity.User;

import java.util.Optional;

@Repository
public interface LoginAndRegisterRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
