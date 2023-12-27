package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.List;

@AllArgsConstructor
@Component
class MealsFinder {

    private final MealsFilterRepository repository;

    public List<FilteredRecipeDto> findMatchingRecipes(InfoForFiltering info) {
        List<String> namesProductsToUse = MealsFilterMapper.mapFromListIngredientDtoToListString(info.productsToUse());

        List<Recipe> result5Req = repository.findRecipesWithAllRequirements(info.forHowManyDays(), info.diet(), info.timeForPrepareMin(), namesProductsToUse, info.dislikedProducts());
        if(result5Req.isEmpty()){
            List<Recipe> result4Req = repository.findRecipesWithoutDislikedProducts(info.forHowManyDays(), info.diet(), info.timeForPrepareMin(), namesProductsToUse);
            if(result4Req.isEmpty()){
                List<Recipe> result3Req = repository.findRecipesWithoutDislikedProductsAndProductsToUse(info.forHowManyDays(), info.diet(), info.timeForPrepareMin());
                if(result3Req.isEmpty()){
                    System.out.println("Nie udało się znaleźć żadnego pasującego przepisu :(");
                }
                return getFilteredRecipesDtoList(result3Req);
            }
            return getFilteredRecipesDtoList(result4Req);
        }
        return getFilteredRecipesDtoList(result5Req);
    }

    private List<FilteredRecipeDto> getFilteredRecipesDtoList(List<Recipe> recipesDb) {
        List<Recipe> choseRecipes = choseMaxFirst10(recipesDb);
        return choseRecipes.stream()
                .map(this::convert)
                .toList();
    }

    /**
     * TODO: Wybieranie przepisów według składników (ilość) zamiast pierwszych 10
     */
    private List<Recipe> choseMaxFirst10(List<Recipe> recipesDb) {
        if(recipesDb.size() > 10){
            return recipesDb.subList(0,10);
        } else {
            return recipesDb;
        }
    }
    private FilteredRecipeDto convert(Recipe choseRecipe) {
        return MealsFilterMapper.mapFromRecipeToFilteredRecipeDto(choseRecipe);
    }
}
