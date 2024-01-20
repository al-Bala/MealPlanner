package pl.mealplanner.recipe.domain;

import com.mongodb.client.*;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Component;
import pl.mealplanner.recipe.domain.entity.RecipeClass;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class RecipeFetcher {
    private final MongoCollection<Document> collection;
    public RecipeFetcher() {
        MongoClient mongoClient = MongoClients.create("mongodb://admin:admin@localhost:27017/planner");
        MongoDatabase database = mongoClient.getDatabase("planner");
        this.collection = database.getCollection("recipes");
    }

    public List<RecipeClass> fetch(String searchFilter) {
        Document filter = new Document("name", java.util.regex.Pattern.compile(searchFilter, java.util.regex.Pattern.CASE_INSENSITIVE));
        FindIterable<Document> documents = collection.find(filter);
        List<RecipeClass> recipes = new ArrayList<>();

        for (Document document : documents) {
            RecipeClass recipe = new RecipeClass();
            recipe.setId(document.getObjectId("_id").toString()); // Ustawianie ID
            recipe.setName(document.getString("name"));
            recipes.add(recipe);
        }

        return recipes;
    }
}
