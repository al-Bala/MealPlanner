package pl.mealplanner.home;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;

@AllArgsConstructor
@Controller
class HomeController {
    PlanGeneratorFacade planGeneratorFacade;
    @GetMapping("/home")
    public String home(Model view) {

        //View: we could add attributes to the view
        view.addAttribute("message", "Meal Planner" );

        //planGeneratorFacade.generateMealPlanner();
        //name of the jsp
        return "home/home-guest";
    }

    @GetMapping("/home/user")
    public String homeUser(Model view) {

        //View: we could add attributes to the view
        view.addAttribute("message", "Meal Planner" );
        //name of the jsp
        return "home/home-user";
    }
}
