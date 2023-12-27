package pl.mealplanner.profile.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Dodatkowe metody repozytorium, jeśli są potrzebne

    User findByEmail(String email);
}
