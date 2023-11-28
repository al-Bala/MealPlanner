//package pl.mealplanner.infrastructure.security.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//import pl.mealplanner.loginandregister.domain.dto.UserDto;
//import pl.mealplanner.loginandregister.infrastructure.dto.JwtResponseDto;
//
//import java.time.*;
//
//@AllArgsConstructor
//@Component
//public class JwtAuthenticatorFacade {
//
//    private final AuthenticationManager authenticationManager;
//    private final Clock clock;
//
//    public JwtResponseDto authenticateAndGenerateToken(UserDto userDto) {
//        // szuka w bazie użytkownika
//        Authentication authenticate = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password()));
//        User user = (User) authenticate.getPrincipal();
//        String token = createToken(user);
//        String username = user.getUsername();
//        return JwtResponseDto.builder()
//                .token(token)
//                .username(username)
//                .build();
//    }
//
//    private String createToken(User user) {
//        String secretKey = "KEY";
//        Algorithm algorithm = Algorithm.HMAC256(secretKey);
//        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
//        Instant expiresAt = now.plus(Duration.ofDays(30));
//        String issuer = "Meal Planner";
//        return JWT.create()
//                .withSubject(user.getUsername())
//                .withIssuedAt(now)
//                .withExpiresAt(expiresAt)
//                .withIssuer(issuer)
//                .sign(algorithm);
//    }
//}
