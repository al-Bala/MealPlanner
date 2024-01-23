package pl.mealplanner.plangenerator.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;
import pl.mealplanner.plangenerator.infrastructure.dto.*;
import pl.mealplanner.plangenerator.plan.dto.DisplayPlan;
import pl.mealplanner.plangenerator.plan.dto.DisplayRecipe;
import pl.mealplanner.plangenerator.productscounter.ProductsCounterFacade;
import pl.mealplanner.plangenerator.productscounter.dto.GroceryList;
import pl.mealplanner.plangenerator.productscounter.entity.Product;
import pl.mealplanner.plangenerator.productscounter.entity.ProductClass;

import java.util.*;

@AllArgsConstructor
@Controller
@RequestMapping("/plan")
class PlanGeneratorController {

    private final PlanGeneratorFacade planGeneratorFacade;
    private final ProductsFetcher productsFetcher;
    private final ProductsCounterFacade productsCounterFacade;
    private final String bucketName = "mealplannerup.s3.";
    private final String region = "eu-north-1";

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
    public String addInfo(@ModelAttribute("info") PlanRequest planRequest,
                          BindingResult result) {
        planGeneratorFacade.generateMealPlanner(planRequest.getPreferences(), planRequest.getWeekInfo());
        return "redirect:/plan/recipes";
    }

    @GetMapping("/recipes")
    public ModelAndView info() {
        List<DisplayPlan> mealPlanner = planGeneratorFacade.getCurrentPlan();
        List<GroceryList> groceryList = planGeneratorFacade.getGroceryListForPlan();

        Map<String, String> imageUrlMap = new HashMap<>();
        mealPlanner.forEach(recipe -> {
            String imageUrl = "https://" + bucketName + region + ".amazonaws.com/" + recipe.firstDisplayRecipe().id() + ".jpg";
            imageUrlMap.put(recipe.firstDisplayRecipe().id(), imageUrl);
        });

        ModelAndView modelAndView = new ModelAndView("plangenerator/planner");
        modelAndView.addObject("imageUrlMap", imageUrlMap);
        modelAndView.addObject("mealPlanner", mealPlanner);
        modelAndView.addObject("groceryList", groceryList);
        return modelAndView;
    }

    @GetMapping("/recipes/{id}")
    public String getRecipe(@PathVariable String id, Model model) {
        DisplayRecipe planRecipe = planGeneratorFacade.getRecipeFromPlan(id);
        String imageUrl = "https://" + bucketName + region + ".amazonaws.com/" + id +".jpg";

        model.addAttribute("diet", planRecipe.diet());
        model.addAttribute("prepareTime", planRecipe.prepare_time());
        model.addAttribute("portions", planRecipe.portions());
        model.addAttribute("name", planRecipe.name());
        model.addAttribute("ingredients", planRecipe.ingredients());
        model.addAttribute("steps", planRecipe.steps());
        model.addAttribute("imageUrl", imageUrl);
        model.addAttribute("id", planRecipe.id());
        return "details/recipe-details";
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
        if(product == null){
            return Collections.emptyList();
        }
        return product.packingUnits();
    }
}
