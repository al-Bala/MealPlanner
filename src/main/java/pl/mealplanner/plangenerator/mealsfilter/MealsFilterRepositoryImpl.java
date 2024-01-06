package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.List;
@AllArgsConstructor
@Log4j2
@Repository
class MealsFilterRepositoryImpl implements MealsFilterRepository{
    private final MongoTemplate mongoTemplate;
    public List<Recipe> findMatchingRecipes(InfoForFiltering info) {
        List<String> namesProductsToUse = MealsFilterMapper.mapFromListIngredientDtoToListString(info.productsToUse());

        Query query1 = new Query();
        Query query2 = new Query();
        Query query3 = new Query();

        Criteria criteriaMaxStorageTime = Criteria.where("max_storage_time").gte(info.forHowManyDays());
        Criteria criteriaDiet = choseDiet(info.diet());
        Criteria criteriaPrepareTime = Criteria.where("prepare_time").lte(info.timeForPrepareMin());
        Criteria criteriaProductsToUse = Criteria.where("ingredients.name").all(namesProductsToUse);
        Criteria criteriaToUseAndDislikedProducts = chose2(namesProductsToUse, info.dislikedProducts());

        List<Criteria> c1 = List.of(criteriaMaxStorageTime, criteriaDiet, criteriaPrepareTime, criteriaToUseAndDislikedProducts);
        List<Criteria> c2 = List.of(criteriaMaxStorageTime, criteriaDiet, criteriaPrepareTime, criteriaProductsToUse);
        List<Criteria> c3 = List.of(criteriaMaxStorageTime, criteriaDiet, criteriaPrepareTime);

        extracted(c1, query1);
        extracted(c2, query2);
        extracted(c3, query3);

        List<Recipe> result5Req = mongoTemplate.find(query1, Recipe.class);
        if(result5Req.isEmpty()){
            List<Recipe> result4Req = mongoTemplate.find(query2, Recipe.class);
            if(result4Req.isEmpty()){
                List<Recipe> result3Req = mongoTemplate.find(query3, Recipe.class);
                if(result3Req.isEmpty()){
                    log.error("Nie udało się znaleźć żadnego pasującego przepisu :(");
                }
                return result3Req;
            }
            return result4Req;
        }
        return result5Req;
    }

    private static void extracted(List<Criteria> criteria, Query query) {
        for (Criteria c: criteria) {
            if(c.getKey() != null){
                query.addCriteria(c);
            }
        }
    }

    private Criteria chose2(List<String> namesProductsToUse, List<String> dislikedProducts){
        if (!namesProductsToUse.isEmpty() && !dislikedProducts.isEmpty()) {
            return Criteria.where("ingredients.name").all(namesProductsToUse).nin(dislikedProducts);
        }
        else if (!namesProductsToUse.isEmpty()) {
            return Criteria.where("ingredients.name").all(namesProductsToUse);
        }
        else if (!dislikedProducts.isEmpty()) {
            return Criteria.where("ingredients.name").nin(dislikedProducts);
        }
        return new Criteria();
    }

    private Criteria choseDiet(String diet){
        if(!diet.isEmpty()){
            return Criteria.where("diet").is(diet);
        }
        return new Criteria();
    }
}
