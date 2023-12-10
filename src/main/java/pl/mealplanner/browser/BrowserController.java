package pl.mealplanner.browser;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BrowserController {

    @GetMapping("/wyszukiwarka")
    public String showSearchPage(Model model) {
        model.addAttribute("pageTitle", "Wyszukiwarka przepisów");
        return "browser/browser"; // Zwróć nazwę widoku dla wyszukiwarki
    }
}
