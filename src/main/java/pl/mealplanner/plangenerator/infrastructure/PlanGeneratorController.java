package pl.mealplanner.plangenerator.infrastructure;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;
import pl.mealplanner.plangenerator.infrastructure.dto.*;
import pl.mealplanner.plangenerator.mealsfilter.dto.MealPlanElement;
import pl.mealplanner.plangenerator.plan.dto.DisplayPlan;
import pl.mealplanner.plangenerator.productscounter.ProductsCounterFacade;
import pl.mealplanner.plangenerator.productscounter.dto.GroceryList;
import pl.mealplanner.plangenerator.productscounter.entity.Product;
import pl.mealplanner.plangenerator.productscounter.entity.ProductClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/plan")
class PlanGeneratorController {

    private final PlanGeneratorFacade planGeneratorFacade;
    private final ProductsFetcher productsFetcher;
    private final ProductsCounterFacade productsCounterFacade;

    @GetMapping("/guest")
    public String loginToGetPlan() {
        return "redirect:/login?planAth";
    }

    @GetMapping("/generator")
    public ModelAndView generatePlan() {

        ModelAndView modelAndView = new ModelAndView("plangenerator/plan-info");

        PlanRequest planRequest = new PlanRequest(
                new UserPreferencesRequest(
                        List.of(new IngredientRequest()),
                        List.of(new DislikedProductRequest())),
                new WeekInfoRequest(
                        List.of(new DayInfoRequest(new EatingPlansRequest())))
        );
        modelAndView.addObject("info", planRequest);
        return modelAndView;
    }

    @PostMapping("/info")
    public ModelAndView addInfo(@ModelAttribute("info") PlanRequest planRequest,
                          BindingResult result,
                          Model model) {
        ModelAndView modelAndView = new ModelAndView("plangenerator/planner");
        List<DisplayPlan> mealPlanner = planGeneratorFacade.generateMealPlanner(planRequest.getPreferences(), planRequest.getWeekInfo());
        modelAndView.addObject("mealPlanner", mealPlanner);
        return modelAndView;
    }

    @GetMapping("/groceryList")
    public ModelAndView getGroceryList() {

        List<GroceryList> groceryList = planGeneratorFacade.getGroceryListForPlan();
        ModelAndView modelAndView = new ModelAndView("plangenerator/grocery-list");
        modelAndView.addObject("groceryList", groceryList);
        return modelAndView;
    }

    @GetMapping("/saved-plan")
    public ModelAndView getPlan() {

        List<DisplayPlan> plan = planGeneratorFacade.getCurrentPlan();
        ModelAndView modelAndView = new ModelAndView("plangenerator/saved-plan");
        modelAndView.addObject("plan", plan);
        return modelAndView;
    }

    @RequestMapping(value="/productNamesAutocomplete")
    @ResponseBody
    public List<String> productNamesAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term)  {
        List<String> suggestions = new ArrayList<>();

        List<ProductClass> allProducts = productsFetcher.fetchProducts(term);

        for (ProductClass product : allProducts) {
            suggestions.add(product.getName());
            }
        return suggestions;

    }

    @RequestMapping(value="/unitOptions")
    @ResponseBody
    public List<String> getUnitOptions(@RequestParam(value="productName", required = false, defaultValue="") String productName) {
        Product product = productsCounterFacade.findProductByName(productName);
        return product.packingUnits();
    }
}
