package pl.mealplanner.plangenerator.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mealplanner.plangenerator.domain.dto.UserPreferencesDto;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoDto;

@Controller
class PlanGeneratorController {
    @GetMapping("/plan")
    public ModelAndView generatePlan() {
        ModelAndView modelAndView = new ModelAndView("plangenerator/plan");
        modelAndView.addObject("preferences", UserPreferencesDto.builder().build());
        return modelAndView;
    }

    @GetMapping("/plan/guest")
    public ModelAndView loginToGetPlan() {
        ModelAndView modelAndView = new ModelAndView("loginandregister/login-page");
        modelAndView.addObject("guestGetPlan", true);
        return modelAndView;
    }

    @PostMapping("/preferences")
    public String addPreferences(@ModelAttribute("preferences") UserPreferencesDto preferencesDto,
                                 @ModelAttribute("weekInfo") WeekInfoDto weekInfoDto,
                                 BindingResult result,
                                 Model model){
//        UserDto existingUser = facade.findUserByEmail(userDto.email());
//
//        if(existingUser != null && existingUser.email() != null && !existingUser.email().isEmpty()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//        }

//        if(result.hasErrors()){
//            model.addAttribute("user", userDto);
//            return "/loginandregister/register";
//        }

//        facade.saveUser(userDto);
        return "redirect:/plangenerator/plan?success";
    }

}
