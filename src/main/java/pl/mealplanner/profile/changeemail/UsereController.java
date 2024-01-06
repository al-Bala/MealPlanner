package pl.mealplanner.profile.changeemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mealplanner.loginandregister.domain.entity.User;

@Controller
public class UsereController {
    @Autowired
    private UsereService usereService;

    @GetMapping("/change-email")
    public String changeEmailForm( Model model) {

        Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = userDetails.getName();
        //Usere usere = usereService.findByEmail(currentUsername);
        Usere usere = usereService.getUserByUsername(currentUsername);
        String usere2 = usere.getUsername();

        model.addAttribute("usere", currentUsername);

        return "change-email";
    }

    @PostMapping("/change-email")
    public String changeEmailSubmit(@RequestParam("newEmail") String newEmail,
                                    @RequestParam("newPassword") String newPassword

    ) {
        Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = userDetails.getName();
        Usere usere = usereService.getUserByUsername(currentUsername);

        if (usere != null) {
            // Sprawdź, czy nowy adres email jest wolny
            if (usereService.findByEmail(newEmail) == null) {
                usere.setEmail(newEmail);
                // Zaktualizuj hasło, jeśli zostało wprowadzone
                if (isValid(newPassword)){
                    // Tutaj możesz zaimplementować kod kodowania nowego hasła
                    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                   String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
                    usere.setPassword(encodedPassword);
                }
                usereService.saveUser(usere);
                return "redirect:/profile"; // Przekierowanie do strony profilu lub innego widoku
            } else {
                // Obsłuż błąd - nowy adres email jest już w użyciu
                return "redirect:/change-email?error=email-in-use";
            }
        } else {
            // Obsłuż błąd - użytkownik nie został znaleziony
            return "redirect:/error-page"; // Przekierowanie na stronę błędu
        }



    }

    private boolean isValid(String password) {
        if (password == null) {
            return false;
        }
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
    }

}
