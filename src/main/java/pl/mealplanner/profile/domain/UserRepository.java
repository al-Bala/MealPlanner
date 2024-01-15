package pl.mealplanner.profile.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends MongoRepository<User, String> {
    // Dodatkowe metody repozytorium, jeśli są potrzebne

    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
