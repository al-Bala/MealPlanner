package pl.mealplanner.profile.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mealplanner.profile.infrastructure.UserService;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{email}")
    public String showUserDetails(@PathVariable String email, Model model) {
        User user = userService.getUserByEmail(email);

        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("role", user.getRole());
            return "user-details"; // nazwa widoku (user-details.html)
        } else {
            return "user-details"; // widok informujący o braku użytkownika
        }
    }
}