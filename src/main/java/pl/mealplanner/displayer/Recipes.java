package pl.mealplanner.displayer;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.mealplanner.recipe.domain.Ingredient;


import java.util.List;

@Document(collection = "recipes")
public class Recipes {

    @Id
    @Getter
    @Setter
    private String id; // ObjectId jako String
    @Getter
    @Setter
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
    @Getter
    private String image;


    @Override
    public String toString() {
        return "Nazwa ='" + name + '\'' +
                ", dieta ='" + diet + '\'' +
                '.';
    }

    public String nazwa() {
        return name;
    }
    // Getters and setters
}




