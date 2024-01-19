package pl.mealplanner.profile.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.mealplanner.loginandregister.domain.dto.Role;
import pl.mealplanner.plangenerator.productscounter.dto.GroceryList;
import pl.mealplanner.profile.domain.entity.PlanHistory;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    ObjectId id;

    @Field("role")
    Role role;

    @Field("username")
    @Indexed(unique = true)
    String username;

    @Field("email")
    @Indexed(unique = true)
    String email;

    @Field("password") String password;

    @Field("plan_history")
    List<PlanHistory> planHistory;

    @Field("grocery_list")
    List<GroceryList> groceryList;

    List<String> favorites;

//    @Getter
//    @Id
//    private String id;
//    @Getter
//    private String email;
//    @Getter
//    private String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Gettery, settery i konstruktor
}


