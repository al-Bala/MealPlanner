package pl.mealplanner.searcher;

import com.mongodb.client.*;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Component;
import pl.mealplanner.displayer.Recipes;
import pl.mealplanner.plangenerator.leftproductscounter.entity.ProductClass;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class SearcherFetcher {
    private final MongoCollection<Document> collection;
    public SearcherFetcher() {
        MongoClient mongoClient = MongoClients.create("mongodb://admin:admin@localhost:27017/planner");
        MongoDatabase database = mongoClient.getDatabase("planner");
        this.collection = database.getCollection("recipes");
    }

    public List<Recipes> fetch(String searchFilter) {
        Document filter = new Document("name", java.util.regex.Pattern.compile(searchFilter, java.util.regex.Pattern.CASE_INSENSITIVE));
        FindIterable<Document> documents = collection.find(filter);
        List<Recipes> recipes = new ArrayList<>();

        for (Document document : documents) {
            Recipes recipe = new Recipes();
            recipe.setId(document.getObjectId("_id").toString()); // Ustawianie ID
            recipe.setName(document.getString("name"));
            recipes.add(recipe);
        }

        return recipes;
    }
}
