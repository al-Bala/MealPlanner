package com.mealplanner.loginandregister.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LoginAndRegisterRepository extends MongoRepository<User, String> {
    //User save(User user);
}
