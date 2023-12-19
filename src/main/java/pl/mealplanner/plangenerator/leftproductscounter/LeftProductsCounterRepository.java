package pl.mealplanner.plangenerator.leftproductscounter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;

@Repository
interface LeftProductsCounterRepository extends MongoRepository<Product, String> {
    Product findByName(String name);
}
