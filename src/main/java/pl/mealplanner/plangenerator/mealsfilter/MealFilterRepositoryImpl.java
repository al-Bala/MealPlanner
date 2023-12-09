package pl.mealplanner.plangenerator.mealsfilter;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.mealplanner.plangenerator.mealscounter.MealsCounterConfig;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@AllArgsConstructor
class MealFilterRepositoryImpl implements MealFilterRepository{

    private final MealsFilterDatabaseConfig databaseConfig;

    @Override
    public List<String> findOneMatchingRecipe(int forHowManyDays, String diet, int timeForPrepareMin, List<String> dislikedProducts, List<String> productsToUse) {
        List<String> foundRecipes = new ArrayList<>();

        Bson query = Filters.and(
                Filters.in("max_storage_time", forHowManyDays),
                Filters.in("diet", diet),
                Filters.in("prepare_time", timeForPrepareMin)
        );

        for (Document result : databaseConfig.getMongoCollection().find(query)) {
            foundRecipes.add(result.toString());
        }

        return foundRecipes;
    }
}

