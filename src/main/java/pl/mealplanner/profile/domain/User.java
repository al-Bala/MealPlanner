package pl.mealplanner.profile.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {

    @Getter
    @Id
    private String id;
    @Getter
    private String email;
    @Getter
    private String role;

    @Getter @Setter
    private List<String> favorites;


    public String getID() {
        return id;
    }

    // Gettery, settery i konstruktor
}


