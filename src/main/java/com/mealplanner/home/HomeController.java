package com.mealplanner.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {
    @GetMapping("/")
    public String home(Model view) {

        //View: we could add attributes to the view
        view.addAttribute("message", "Meal Planner" );
        //name of the jsp
        return "home/index";
    }
}
