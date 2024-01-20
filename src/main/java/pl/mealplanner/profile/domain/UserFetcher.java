package pl.mealplanner.profile.domain;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import pl.mealplanner.recipe.domain.entity.RecipeClass;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
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

    public List<RecipeClass> fetchFavorites(String userId, String searchFilter) {
        Document user = userCollection.find(eq("_id", new ObjectId(userId))).first();
        if (user == null) {
            return new ArrayList<>();
        }

        List<ObjectId> favoriteIds = user.getList("favorites", ObjectId.class);
        List<RecipeClass> favoriteRecipes = new ArrayList<>();

        for (ObjectId favoriteId : favoriteIds) {
            Document doc = recipeCollection.find(eq("_id", favoriteId)).first();
            if (doc != null) {
                String name = doc.getString("name");
                if (name.toLowerCase().contains(searchFilter.toLowerCase())) {
                    RecipeClass recipe = new RecipeClass();
                    recipe.setId(doc.getObjectId("_id").toString());
                    recipe.setName(name);
                    favoriteRecipes.add(recipe);
                }
            }
        }

        return favoriteRecipes;
    }
}
