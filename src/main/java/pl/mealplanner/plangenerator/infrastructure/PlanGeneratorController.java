package pl.mealplanner.plangenerator.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;
import pl.mealplanner.plangenerator.domain.dto.Eating;
import pl.mealplanner.plangenerator.infrastructure.dto.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/plan")
class PlanGeneratorController {

    private final PlanGeneratorFacade planGeneratorFacade;

    @GetMapping("/guest")
    public String loginToGetPlan() {
        return "redirect:/login?planAth";
    }

    @GetMapping("/generator")
    public ModelAndView generatePlan() {
        ModelAndView modelAndView = new ModelAndView("plangenerator/plan-info");
//        modelAndView.addObject("eatingStatuses", Eating.EatingStatus.values());

        modelAndView.addObject("info",
                new PlanRequest(
                        new UserPreferencesRequest(
                                List.of(new IngredientRequest()),
                                List.of(new DislikedProductRequest())),
                        new WeekInfoRequest(
                                List.of(new DayInfoRequest(new Eating())))
                ));
        return modelAndView;
    }

    @PostMapping("/info")
    public String addInfo(@ModelAttribute("info") PlanRequest planRequest,
                                BindingResult result,
                                Model model) {
//        List<FilteredRecipeDto> recipesPlan = planGeneratorFacade.generateMealPlanner(planRequest.getPreferences(), planRequest.getWeekInfo());
//        System.out.println(recipesPlan);
        return "plangenerator/planner";
    }
}
