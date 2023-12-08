package pl.mealplanner.home;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;
import pl.mealplanner.plangenerator.domain.dto.DayInfo;
import pl.mealplanner.plangenerator.domain.dto.EatingPlans;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoDto;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Controller
class HomeController {
    PlanGeneratorFacade planGeneratorFacade;
    @GetMapping("/home")
    public String home(Model view) {

        view.addAttribute("message", "Meal Planner" );

        WeekInfoDto weekInfo = new WeekInfoDto(List.of(
                new DayInfo(LocalDate.of(2023, 12,9), new EatingPlans("C01", 30)),
                new DayInfo(LocalDate.of(2023, 12,10), new EatingPlans("B02", 0)),
                new DayInfo(LocalDate.of(2023, 12,11), new EatingPlans("E03", 0)),
                new DayInfo(LocalDate.of(2023, 12,12), new EatingPlans("C01", 60)),
                new DayInfo(LocalDate.of(2023, 12,13), new EatingPlans("E03", 0)),
                new DayInfo(LocalDate.of(2023, 12,14), new EatingPlans("C01", 40)),
                new DayInfo(LocalDate.of(2023, 12,15), new EatingPlans("E03", 0))
        ));

        planGeneratorFacade.generateMealPlanner(weekInfo);
        return "home/home-guest";
    }

    @GetMapping("/home/user")
    public String homeUser(Model view) {
        view.addAttribute("message", "Meal Planner" );
        return "home/home-user";
    }
}
