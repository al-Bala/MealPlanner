package pl.mealplanner.plangenerator.infrastructure;

import jakarta.validation.Valid;
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
import pl.mealplanner.plangenerator.infrastructure.dto.PlanRequest;

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
        modelAndView.addObject("eatingStatuses", Eating.EatingStatus.values());

        modelAndView.addObject("info", new PlanRequest());
        return modelAndView;
    }

    @PostMapping("/info")
    public String addInfo(@ModelAttribute("info") @Valid PlanRequest planRequest,
                                BindingResult result,
                                Model model) {

//        List<FilteredRecipeDto> planner = planGeneratorFacade.generateMealPlanner(preferencesDto, weekInfoDto);
//        modelAndView.addObject("planner", planner);
        return "plangenerator/planner";
    }
}
