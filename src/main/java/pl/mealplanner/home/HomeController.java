package pl.mealplanner.home;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
class HomeController {


    @GetMapping("/home")
    public String home(Model View) {


        View.addAttribute("message", "Meal Planner");

        return "home/home-guest";
    }

    @GetMapping("/home/user")
    public String homeUser(Model view) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Załóżmy, że ID użytkownika jest przechowywane jako nazwa użytkownika

        //view.addAttribute("userId", userId);
        view.addAttribute("message", "Meal Planner");
        view.addAttribute("username", username);
        return "home/home-user";
    }
}


