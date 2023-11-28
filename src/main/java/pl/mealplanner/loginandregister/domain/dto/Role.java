package pl.mealplanner.loginandregister.domain.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role{
    //GUEST("GUEST"),
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

//    public List<SimpleGrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
//    }
}
