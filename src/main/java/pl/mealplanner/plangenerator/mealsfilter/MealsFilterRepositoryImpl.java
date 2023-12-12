package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

@AllArgsConstructor
class MealsFilterRepositoryImpl implements MealsFilterRepository {
    private MongoTemplate mongoTemplate;
    private final String collection;

    @Override
    public FilteredRecipeDto findOneMatchingRecipe(int forHowManyDays, String diet, int timeForPrepareMin, List<String> productsToUse, List<String> dislikedProducts) {

        Bson firstQuery = and(
                gte("max_storage_time", forHowManyDays),
                in("diet", diet),
                lte("prepare_time", timeForPrepareMin),
                all("ingredients.name", productsToUse),
                nin("ingredients.name", dislikedProducts)
        );

        Bson secoundQuery = and(
                gte("max_storage_time", forHowManyDays),
                in("diet", diet),
                lte("prepare_time", timeForPrepareMin),
                all("ingredients.name", productsToUse)
        );

        Bson thirdQuery = and(
                gte("max_storage_time", forHowManyDays),
                in("diet", diet),
                lte("prepare_time", timeForPrepareMin)
        );

        List<Document> records1 = query(firstQuery);
        if (records1.isEmpty()) {
            List<Document> records2 = query(secoundQuery);
            if (records2.isEmpty()) {
                List<Document> records3 = query(thirdQuery);
                if (records3.isEmpty()) {
                    System.out.println("Nie udało się znaleźć żadnego pasującego przepisu :(");
                }
                return getOneFilteredRecipeDto(records3);
            }
            return getOneFilteredRecipeDto(records2);
        }
        return getOneFilteredRecipeDto(records1);
    }

    private List<Document> query(Bson query) {
        List<Document> records = new ArrayList<>();
        for (Document result : mongoTemplate.getCollection(collection).find(query)) {
            records.add(result);
        }
        return records;
    }

    private FilteredRecipeDto getOneFilteredRecipeDto(List<Document> recipesDb) {
        Document choseRecipe = choseOne(recipesDb);
        return convert(choseRecipe);
    }

    private Document choseOne(List<Document> recipesDb) {
        return recipesDb.get(0);
    }

    private FilteredRecipeDto convert(Document choseRecipe) {
        Recipe recipe = mongoTemplate.getConverter().read(Recipe.class, choseRecipe);
        return RecipeMapper.mapFromRecipeToFilteredRecipeDto(recipe);
    }
}

