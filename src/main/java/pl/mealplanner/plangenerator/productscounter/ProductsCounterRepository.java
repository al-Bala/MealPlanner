package pl.mealplanner.plangenerator.productscounter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mealplanner.plangenerator.productscounter.entity.Product;

import java.util.Optional;

@Repository
interface ProductsCounterRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);
}
