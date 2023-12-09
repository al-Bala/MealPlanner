package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;

import java.util.List;

@AllArgsConstructor
@Service
class MealsFilterService {

    private final MealFilterRepository mealFilterRepository;

    public List<String> findMeals(InfoForMealsSearch infoForMealsSearch){

        List<String> foundRecipes = infoForMealsSearch.oneMealInfoList().stream()
                .map(oneMealInfo -> mealFilterRepository.findOneMatchingRecipe(
                        oneMealInfo.forHowManyDays(),
                        infoForMealsSearch.preferencesDto().diet(),
                        oneMealInfo.timeForPrepareMin(),
                        infoForMealsSearch.preferencesDto().productsToUse(),
                        infoForMealsSearch.preferencesDto().dislikedProducts()).toString())
                .toList();

        return foundRecipes;

        //return null;
    }
    String checkPreviousWeek(){
        return null;
    }
    String calculateIngredients(){
        return null;
    }
    String saveTheLastLeftovers(){
        return null;
    }
}
