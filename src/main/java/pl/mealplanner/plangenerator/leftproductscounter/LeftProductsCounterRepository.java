package pl.mealplanner.plangenerator.leftproductscounter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;

import java.util.Optional;

@Repository
interface LeftProductsCounterRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);
}
