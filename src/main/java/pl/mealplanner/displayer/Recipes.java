package pl.mealplanner.displayer;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.mealplanner.recipe.domain.Ingredient;

import java.util.List;

@Document(collection = "recipes")
public class Recipes {

    @Id
    @Getter
    private String id; // ObjectId jako String
    @Getter
    private String name;
    @Getter
    private String diet;

    @Getter
    private List<Ingredient> ingredients;
    @Getter
    private int portions;
    @Getter
    private String[] steps;
    @Getter
    private int prepareTime;
    @Getter
    private int maxStorageTime;
    // ... other fields


    @Override
    public String toString() {
        return "Nazwa ='" + name + '\'' +
                ", dieta ='" + diet + '\'' +
                '.';
    }

    public String nazwa() {
        return "Nazwa przepisu: " + name;
    }
    // Getters and setters
}




