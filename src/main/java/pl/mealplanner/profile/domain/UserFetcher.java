package pl.mealplanner.profile.domain;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import pl.mealplanner.displayer.Recipes;
import pl.mealplanner.plangenerator.leftproductscounter.entity.ProductClass;
import pl.mealplanner.searcher.SearcherFetcher;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
@AllArgsConstructor
@Component

public class UserFetcher {
    private final MongoCollection<Document> userCollection;
    private final MongoCollection<Document> recipeCollection;

    public UserFetcher() {
        MongoClient mongoClient = MongoClients.create("mongodb://admin:admin@localhost:27017/planner");
        MongoDatabase database = mongoClient.getDatabase("planner");
        this.userCollection = database.getCollection("users");
        this.recipeCollection = database.getCollection("recipes");
    }

    public List<Recipes> fetchFavorites(String userId, String searchFilter) {
        Document user = userCollection.find(eq("_id", new ObjectId(userId))).first();
        if (user == null) {
            return new ArrayList<>();
        }

        List<ObjectId> favoriteIds = user.getList("favorites", ObjectId.class);
        List<Recipes> favoriteRecipes = new ArrayList<>();

        for (ObjectId favoriteId : favoriteIds) {
            Document doc = recipeCollection.find(eq("_id", favoriteId)).first();
            if (doc != null) {
                String name = doc.getString("name");
                if (name.toLowerCase().contains(searchFilter.toLowerCase())) {
                    Recipes recipe = new Recipes();
                    recipe.setId(doc.getObjectId("_id").toString());
                    recipe.setName(name);
                    favoriteRecipes.add(recipe);
                }
            }
        }

        return favoriteRecipes;
    }
}
