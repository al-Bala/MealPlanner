package pl.mealplanner.profile.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Getter
    @Id
    private String id;
    @Getter
    private String email;
    @Getter
    private String role;


    public String getID() {
        return id;
    }

    // Gettery, settery i konstruktor
}


