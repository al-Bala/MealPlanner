package pl.mealplanner.loginandregister.domain.entity;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.mealplanner.loginandregister.domain.dto.Role;

import java.util.Collection;
import java.util.List;

@Builder
@Document("users")
public record User(

        @Id ObjectId id,
        @Field("role") Role role,

        @Field("username")
        @Indexed(unique = true)
        String username,
        @Field("email")
        @Indexed(unique = true)
        String email,
        @Field("password") String password,
//        @Field("user_preferences") UserPreferences preferences,
//        @Field("user_recipes") List<Long> userRecipes,
        @Field("plan_history") List<PlanHistory> planHistory
) implements UserDetails {
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
        return email;
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
}
