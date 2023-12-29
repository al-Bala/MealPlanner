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
import pl.mealplanner.plangenerator.domain.dto.UserPreferencesDto;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoDto;

@AllArgsConstructor
@Controller
@RequestMapping("/plan")
class PlanGeneratorController {

    private final PlanGeneratorFacade planGeneratorFacade;

    @GetMapping("/info")
    public ModelAndView generatePlan() {
        ModelAndView modelAndView = new ModelAndView("plangenerator/plan-info");
        modelAndView.addObject("preferences", UserPreferencesDto.builder().build());
        modelAndView.addObject("weekInfo", WeekInfoDto.builder().build());
        return modelAndView;
    }

    @GetMapping("/guest")
    public String loginToGetPlan() {
        return "redirect:/login?planAth";
    }

    @PostMapping("/preferences")
    public ModelAndView addPreferences(@ModelAttribute("preferences") UserPreferencesDto preferencesDto,
//                                 @ModelAttribute("weekInfo") WeekInfoDto weekInfoDto,
                                 BindingResult result,
                                 Model model){
        ModelAndView modelAndView = new ModelAndView("plangenerator/planner");

//        List<FilteredRecipeDto> planner = planGeneratorFacade.generateMealPlanner(preferencesDto, weekInfoDto);
//        modelAndView.addObject("planner", planner);
        return modelAndView;
    }
}
