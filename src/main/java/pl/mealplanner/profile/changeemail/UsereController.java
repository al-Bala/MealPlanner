package pl.mealplanner.profile.changeemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsereController {
    @Autowired
    private UsereService usereService;

    @GetMapping("/user/{username}/change-profil")
    public String changeEmailForm( @PathVariable String username, Model model) {
        Usere user = usereService.getUserByUsername(username);

        Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = userDetails.getName();
        Usere usere = usereService.getUserByUsername(currentUsername);

        model.addAttribute("usere", currentUsername);

        return "details/user-change";
    }

    @PostMapping("/change-email")
    public String changeEmailSubmit(@RequestParam("newEmail") String newEmail,
                                    @RequestParam("newPassword") String newPassword,
                                    @RequestParam("confirmNewPassword") String confirmNewPassword,
                                    RedirectAttributes redirectAttributes) {
        Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = userDetails.getName();
        Usere usere = usereService.getUserByUsername(currentUsername);

        if (usere != null) {
            if (usereService.findByEmail(newEmail) != null) {
                redirectAttributes.addFlashAttribute("error", "Email zajęty");
                return "redirect:/change-email";
            }

            if (!newPassword.equals(confirmNewPassword)) {
                redirectAttributes.addFlashAttribute("error", "Podane hasła nie są jednakowe");
                return "redirect:/change-email";
            }

            if (!isValid(newPassword)) {
                redirectAttributes.addFlashAttribute("error", "Hasło musi zawierać znaki specjalne");
                return "redirect:/change-email";
            }

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
            usere.setPassword(encodedPassword);
            usere.setEmail(newEmail);
            usereService.saveUser(usere);
            return "redirect:/profile";
        } else {
            return "redirect:/error-page";
        }



    }

    private boolean isValid(String password) {
        if (password == null) {
            return false;
        }
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
    }

}
