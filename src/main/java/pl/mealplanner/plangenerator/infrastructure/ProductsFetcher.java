package pl.mealplanner.plangenerator.infrastructure;

import com.mongodb.client.*;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.productscounter.entity.ProductClass;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Component
class ProductsFetcher {

    private final MongoCollection<Document> collection;
    public ProductsFetcher() {
        MongoClient mongoClient = MongoClients.create("mongodb://admin:admin@localhost:27017/planner");
        MongoDatabase database = mongoClient.getDatabase("planner");
        this.collection = database.getCollection("products");
    }

    public List<ProductClass> fetchProducts(String term) {
        Document filter = new Document("name", java.util.regex.Pattern.compile(term, java.util.regex.Pattern.CASE_INSENSITIVE));
        FindIterable<Document> documents = collection.find(filter);

        List<ProductClass> products = new ArrayList<>();
        for (Document document : documents) {
            ProductClass product = new ProductClass();
            product.setName(document.getString("name"));
            products.add(product);
        }
        return products;
    }
}
