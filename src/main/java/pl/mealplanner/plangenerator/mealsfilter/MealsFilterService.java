package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.List;

@AllArgsConstructor
@Service
class MealsFilterService {

    private final MealsFilterRepository mealsFilterRepository;

    public List<FilteredRecipeDto> findMeals(InfoForMealsSearch infoForMealsSearch){

        List<FilteredRecipeDto> foundRecipes = infoForMealsSearch.oneMealInfoList().stream()
                .map(oneMealInfo -> mealsFilterRepository.findOneMatchingRecipe(
                        oneMealInfo.forHowManyDays(),
                        infoForMealsSearch.preferencesDto().diet(),
                        oneMealInfo.timeForPrepareMin(),
                        infoForMealsSearch.preferencesDto().productsToUse(),
                        infoForMealsSearch.preferencesDto().dislikedProducts()))
                .toList();

        return foundRecipes;
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
