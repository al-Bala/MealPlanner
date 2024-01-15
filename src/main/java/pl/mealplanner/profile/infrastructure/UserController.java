package pl.mealplanner.profile.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mealplanner.profile.domain.User;
import pl.mealplanner.profile.domain.UserFacade;

@Controller
class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/user/{username}")
    public String showUserDetails(@PathVariable String username, Model model) {
        User user = userFacade.getUserByUsername(username);

        if (user != null) {
            model.addAttribute("user", user.getUsername());
            model.addAttribute("role", user.getRole());
            return "details/user-details"; // nazwa widoku (user-details.html)
        } else {
            return "details/user-details"; // widok informujący o braku użytkownika
        }
    }
}