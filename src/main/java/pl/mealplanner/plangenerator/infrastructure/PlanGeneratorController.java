package pl.mealplanner.plangenerator.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class PlanGeneratorController {
    @GetMapping("/plan")
    public ModelAndView generatePlan() {
        ModelAndView modelAndView = new ModelAndView("plangenerator/plan");
        modelAndView.addObject("plan", "plan generator");
        return modelAndView;
    }
}
