package pl.mealplanner.loginandregister.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface LoginAndRegisterRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
