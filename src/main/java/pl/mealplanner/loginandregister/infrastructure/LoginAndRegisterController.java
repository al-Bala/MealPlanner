package pl.mealplanner.loginandregister.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mealplanner.loginandregister.domain.LoginAndRegisterFacade;
import pl.mealplanner.loginandregister.domain.dto.UserDto;

@AllArgsConstructor
@Controller
class LoginAndRegisterController {
    private final LoginAndRegisterFacade facade;
    @GetMapping("/login")
    public String login(){
        return "/loginandregister/login-page";
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationForm(){
        // create model object to store form data
        ModelAndView modelAndView = new ModelAndView("/loginandregister/register");
        modelAndView.addObject("user", UserDto.builder().build());
        return modelAndView;
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
//        UserDto existingUser = facade.findUserByEmail(userDto.email());
//
//        if(existingUser != null && existingUser.email() != null && !existingUser.email().isEmpty()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/loginandregister/register";
        }

        facade.saveUser(userDto);
        return "redirect:/loginandregister/register?success";
    }
}
