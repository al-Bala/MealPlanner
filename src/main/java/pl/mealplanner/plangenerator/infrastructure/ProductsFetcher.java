package pl.mealplanner.plangenerator.infrastructure;

import com.mongodb.client.*;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.leftproductscounter.entity.ProductClass;

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

    public List<ProductClass> fetch(String searchFilter) {
        Document filter = new Document("name", java.util.regex.Pattern.compile(searchFilter, java.util.regex.Pattern.CASE_INSENSITIVE));
        FindIterable<Document> documents = collection.find(filter);

        List<ProductClass> plants = new ArrayList<>();
        for (Document document : documents) {
            ProductClass plantDTO = new ProductClass();
            plantDTO.setName(document.getString("name"));
            plants.add(plantDTO);
        }
        return plants;
    }
}
