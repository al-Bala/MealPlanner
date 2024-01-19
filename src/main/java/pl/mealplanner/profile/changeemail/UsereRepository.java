package pl.mealplanner.profile.changeemail;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsereRepository extends MongoRepository<Usere, String> {


    Usere findByEmail(String email);

    Optional<Usere> findByUsername(String username);
}