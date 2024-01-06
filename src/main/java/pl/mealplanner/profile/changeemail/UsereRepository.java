package pl.mealplanner.profile.changeemail;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mealplanner.loginandregister.domain.entity.User;

import java.util.Optional;

public interface UsereRepository extends MongoRepository<Usere, String> {


    Usere findByEmail(String email);

    Optional<Usere> findByUsername(String username);
}