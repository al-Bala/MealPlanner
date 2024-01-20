package pl.mealplanner.recipe.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recipes")
public class RecipeClass {

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
    private int prepare_Time;
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




