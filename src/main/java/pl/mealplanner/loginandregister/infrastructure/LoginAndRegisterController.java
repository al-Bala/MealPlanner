package pl.mealplanner.loginandregister.infrastructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public String loginPage() {
        return "/loginandregister/login-page";
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        // create model object to store form data
        ModelAndView modelAndView = new ModelAndView("/loginandregister/register");
        modelAndView.addObject("user", UserDto.builder().build());
        return modelAndView;
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") @Valid UserDto userDto,
            BindingResult result) {
        if (facade.isUsernameExists(userDto.username())) {
            result.rejectValue("username", "username.exist");
        }
        if (facade.isEmailExists(userDto.email())) {
            result.rejectValue("email", "email.exist");
        }

        if (result.hasErrors()) {
            return "loginandregister/register";
        }

        facade.saveUser(userDto);
        return "redirect:/login?successReg";
    }
}
