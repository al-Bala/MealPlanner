package pl.mealplanner.profile.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mealplanner.profile.domain.UserFacade;
import pl.mealplanner.profile.domain.UserFetcher;
import pl.mealplanner.profile.domain.UserService;
import pl.mealplanner.profile.domain.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
class UserController {

    @Autowired
    private UserFetcher userFetcher; // Wstrzyknięcie zależności

    private final UserFacade userFacade;
    private final UserService userService;

    public UserController(UserFacade userFacade, UserService userService) {
        this.userFacade = userFacade;
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public String showUserDetails(@PathVariable String username, Model model) {
//        User user = userService.getUserByUsername(username);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userFacade.getUserByUsername(username);

        if (user != null) {
            model.addAttribute("user", user.getUsername());
            model.addAttribute("id", user.getId());
            model.addAttribute("role", user.getRole());
            return "details/user-details"; // nazwa widoku (user-details.html)
        } else {
            return "details/user-details"; // widok informujący o braku użytkownika
        }
    }

    /*@RequestMapping(value="/favoriteRecipesAutocomplete")
    @ResponseBody
    public List<Map<String, String>> favoriteRecipesAutocomplete(@RequestParam(value="userId") String userId, @RequestParam(value="term", required = false, defaultValue="") String term) {
        List<Map<String, String>> suggestions = new ArrayList<>();
        List<Recipes> favoriteRecipes = userFetcher.fetchFavorites(userId, term);
        for (Recipes recipe : favoriteRecipes) {
            Map<String, String> suggestion = new HashMap<>();
            suggestion.put("id", recipe.getId());
            suggestion.put("name", recipe.getName());
            suggestions.add(suggestion);
        }
        return suggestions;
    }*/


    @GetMapping("/user/{username}/change-profil")
    public String changeEmailForm( @PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);

        Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = userDetails.getName();
        User usere = userService.getUserByUsername(currentUsername);

        model.addAttribute("usere", currentUsername);

        return "details/user-change";
    }


    @PostMapping("/user/{username}/change-profil-date")
    public String changeEmailSubmit(@RequestParam("newEmail") Optional<String> newEmail,
                                    @RequestParam("newPassword") Optional<String> newPassword,
                                    @RequestParam("confirmNewPassword") Optional<String> confirmNewPassword,
                                    RedirectAttributes redirectAttributes) {

        Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = userDetails.getName();
        User usere = userService.getUserByUsername(currentUsername);

        if (usere != null) {

            boolean emailUpdated = false;
            boolean passwordUpdated = false;

            if (newEmail.isPresent() && !newEmail.get().isEmpty()) {
                if (userService.findByEmail(newEmail.get()) != null) {
                    redirectAttributes.addFlashAttribute("error", "Email zajęty");
                    return "redirect:/user/{username}/change-profil";
                }
                usere.setEmail(newEmail.get());
                emailUpdated = true;
            }

            if (newPassword.isPresent() && !newPassword.get().isEmpty()) {
                if (!confirmNewPassword.isPresent() || !newPassword.get().equals(confirmNewPassword.get())) {
                    redirectAttributes.addFlashAttribute("error", "Podane hasła nie są jednakowe");
                    return "redirect:/user/{username}/change-profil";
                }

                if (!isValid(newPassword.get())) {
                    redirectAttributes.addFlashAttribute("error", "Hasło musi zawierać znaki specjalne");
                    return "redirect:/user/{username}/change-profil";
                }

                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = bCryptPasswordEncoder.encode(newPassword.get());
                usere.setPassword(encodedPassword);
                passwordUpdated = true;
            }

            if (emailUpdated && passwordUpdated) {
                redirectAttributes.addFlashAttribute("message", "Dane zostały zmienione");
            } else if (emailUpdated) {
                redirectAttributes.addFlashAttribute("message", "Email został zmieniony");
            } else if (passwordUpdated) {
                redirectAttributes.addFlashAttribute("message", "Hasło zostało zmienione");
            }

            if (emailUpdated || passwordUpdated) {
                userService.saveUser(usere);
            }

            return "redirect:/user/{username}";
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