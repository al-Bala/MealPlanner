package pl.mealplanner.recipe.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document(collection = "recipes")
public class RecipeClass {
    @Id
    @Setter
    private String id; // ObjectId jako String
    @Setter
    private String name;
    private String diet;
    private List<Ingredient> ingredients;
    private int portions;
    private String[] steps;
    private int prepare_time;
    private int maxStorageTime;
    private String image;
}




