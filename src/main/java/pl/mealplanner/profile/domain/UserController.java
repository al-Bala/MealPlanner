package pl.mealplanner.profile.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mealplanner.loginandregister.domain.entity.User;
import pl.mealplanner.profile.infrastructure.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public String showUserDetails(@PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);

        if (user != null) {
            model.addAttribute("user", user.username());
            model.addAttribute("role", user.role());
            return "details/user-details"; // nazwa widoku (user-details.html)
        } else {
            return "details/user-details"; // widok informujący o braku użytkownika
        }
    }
}