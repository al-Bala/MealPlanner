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
    //private final JwtAuthenticatorFacade jwtAuthenticatorFacade;


//    @GetMapping("/show-login-form")
//    public ModelAndView showLoginForm() {
//        ModelAndView modelAndView = new ModelAndView("loginandregister/login-form");
//        modelAndView.addObject("userDto", UserDto.builder().build());
//        return modelAndView;
//    }
//
//    @PostMapping("/submit-login-from")
//    @ResponseStatus(HttpStatus.OK)
//    public ModelAndView submitLoginForm(@ModelAttribute("userDto") UserDto userDto) {
//        JwtResponseDto user = jwtAuthenticatorFacade.authenticateAndGenerateToken(userDto);
//
////        UserDto foundUserDto = facade.findByUsername(userDto.username());
////        TokenRequest tokenRequest = new TokenRequest(foundUserDto.username(), foundUserDto.password());
////        facade.authenticateAndGenerateToken(tokenRequest);
//
//        ModelAndView modelAndView = new ModelAndView("loginandregister/login-info");
//        modelAndView.addObject("username", user.username());
//        return modelAndView;
//    }
//
//    @GetMapping("/show-signup-form")
//    public ModelAndView showSignupForm() {
//        ModelAndView modelAndView = new ModelAndView("loginandregister/signup-form");
//        modelAndView.addObject("userDto", UserDto.builder().build());
//        return modelAndView;
//    }
//
//    @PostMapping("/submit-signup-from")
//    public String submitSignupForm(@ModelAttribute("userDto") UserDto userDto, Model model) {
//        UserDto savedUserDto = facade.saveUser(userDto);
//
//        //widok
//        model.addAttribute("username", savedUserDto.username());
//        return "loginandregister/signup-info";
//    }

    @GetMapping("/login")
    public String login(){
        return "/loginandregister/login";
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
