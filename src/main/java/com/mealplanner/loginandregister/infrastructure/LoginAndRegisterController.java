package com.mealplanner.loginandregister.infrastructure;

import com.mealplanner.loginandregister.domain.LoginAndRegisterFacade;
import com.mealplanner.loginandregister.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
class LoginAndRegisterController {
    private final LoginAndRegisterFacade facade;

    @PostMapping("/login")
    public String login() {

        return "loginandregister/login";
    }

    @GetMapping("/show-signup-form")
    public String showSignupForm(Model model) {
        model.addAttribute("userDto", UserDto.builder().build());

        return "loginandregister/signup-form";
    }

    @PostMapping("/submit-signup-from")
    public String submitSignupForm(@ModelAttribute("userDto") UserDto userDto, Model view) {
        UserDto savedUserDto = facade.register(userDto);

        view.addAttribute("username", savedUserDto.username());

        return "loginandregister/signup-info";
    }
}
