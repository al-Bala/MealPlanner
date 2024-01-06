package pl.mealplanner.profile.changeemail;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class Usere {
    @Getter
    @Id
    private ObjectId id;
    @Field("email")
    @Getter @Setter
    private String email;
    @Getter
    private String role;
    @Field("username")
    @Getter
    private String username;
    @Getter @Setter
    private String password;


}





