package pl.mealplanner.plangenerator.mealsfilter;

import com.mongodb.client.model.Filters;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
class MealsFilterRepositoryImpl implements MealsFilterRepository {
    private MongoTemplate mongoTemplate;
    private final String collection;

    @Override
    public FilteredRecipeDto findOneMatchingRecipe(int forHowManyDays, String diet, int timeForPrepareMin, List<String> dislikedProducts, List<String> productsToUse) {

        List<FilteredRecipeDto> foundRecipes = new ArrayList<>();

        Bson query = Filters.and(
                Filters.in("max_storage_time", forHowManyDays),
                Filters.in("diet", diet),
                Filters.in("prepare_time", timeForPrepareMin)
        );

        for (Document result : mongoTemplate.getCollection(collection).find(query)) {
            Recipe recipe = mongoTemplate.getConverter().read(Recipe.class, result);
            FilteredRecipeDto filteredRecipe = RecipeMapper.mapFromRecipeToFilteredRecipeDto(recipe);
            foundRecipes.add(filteredRecipe);
        }

        return foundRecipes.get(0);
    }
}

