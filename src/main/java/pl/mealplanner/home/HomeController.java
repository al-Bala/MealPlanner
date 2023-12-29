package pl.mealplanner.home;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mealplanner.displayer.Recipes;
import pl.mealplanner.displayer.RecipesRepository;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;
import pl.mealplanner.plangenerator.domain.dto.DayInfo;
import pl.mealplanner.plangenerator.domain.dto.EatingPlans;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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


