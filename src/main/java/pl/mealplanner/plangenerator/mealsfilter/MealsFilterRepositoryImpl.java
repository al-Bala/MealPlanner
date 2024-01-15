package pl.mealplanner.plangenerator.mealsfilter;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import pl.mealplanner.loginandregister.domain.dto.PlanHistoryDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;
import pl.mealplanner.profile.domain.UserFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Log4j2
@Repository
class MealsFilterRepositoryImpl implements MealsFilterRepository{
    private final MongoTemplate mongoTemplate;
    private final UserFacade userFacade;
    public List<Recipe> findMatchingRecipes(InfoForFiltering info, int limit) {
        List<ObjectId> previousPlanRecipes = getRecipesFromPreviousPlan();

        Criteria criteriaMaxStorageTime = Criteria.where("max_storage_time").gte(info.forHowManyDays());
        Criteria criteriaDiet = isEmptyDiet(info.diet());
        Criteria criteriaPrepareTime = isEmptyPrepareTime(info.timeForPrepareMin());
        List<AggregationOperation> agrProductsToUse = isEmptyProductsToUse(info.productsToUse(), limit);
        Criteria criteriaDislikedProducts = isEmptyDislikedProducts(info.dislikedProducts());
        Criteria criteriaPlanHistory = Criteria.where("_id").nin(previousPlanRecipes);

        List<Criteria> c1 = List.of(criteriaMaxStorageTime, criteriaDiet, criteriaPrepareTime, criteriaDislikedProducts, criteriaPlanHistory);  // +agrProductsToUse
        List<Criteria> c2 = List.of(criteriaMaxStorageTime, criteriaDiet, criteriaPrepareTime, criteriaPlanHistory);    // +agrProductsToUse

        List<AggregationOperation> criteriaList1 = noNullInCriteria(c1);
        List<AggregationOperation> criteriaList2 = noNullInCriteria(c2);

        Aggregation agrWithAllReg = createAggregation(agrProductsToUse, criteriaList1);
        Aggregation agrWithoutDislikedProd = createAggregation(agrProductsToUse, criteriaList2);

        AggregationResults<Recipe> result1 = mongoTemplate.aggregate(agrWithAllReg, "recipes", Recipe.class);
        List<Recipe> documents1 = result1.getMappedResults();
        if(documents1.isEmpty()){
            AggregationResults<Recipe> result2 = mongoTemplate.aggregate(agrWithoutDislikedProd, "recipes", Recipe.class);
            List<Recipe> documents2 = result2.getMappedResults();
            if(documents2.isEmpty()){
                log.error("Nie udało się znaleźć żadnego pasującego przepisu :(");
                return null;
            }
            return documents2;
        }
        return documents1;
    }

    private Aggregation createAggregation(List<AggregationOperation> agrProductsToUse, List<AggregationOperation> criteria) {
        List<AggregationOperation> combinedOperations = new ArrayList<>(criteria);
        combinedOperations.addAll(agrProductsToUse);
        return Aggregation.newAggregation(combinedOperations);
    }

    private List<ObjectId> getRecipesFromPreviousPlan(){
        List<PlanHistoryDto> planHistoryList = userFacade.findPlanHistoryByCurrentUser();
        return planHistoryList.stream()
                .map(PlanHistoryDto::recipeId)
                .toList();
    }

    private List<AggregationOperation> noNullInCriteria(List<Criteria> criteriaList) {
        List<AggregationOperation> agr = new ArrayList<>();
        for (Criteria c: criteriaList) {
            if(c.getKey() != null){
                agr.add(Aggregation.match(c));
            }
        }
        return agr;
    }

    // NA PEWNO znjadzie przepis z przynjamniej 1 productToUse
    private List<AggregationOperation> isEmptyProductsToUse(List<String> namesProductsToUse, int limit) {
        if (!namesProductsToUse.isEmpty()) {
            return Arrays.asList(
                    Aggregation.match(Criteria.where("ingredients.name").in(namesProductsToUse)),
                    Aggregation.project()
//                            .andInclude("_id", "name", "ingredients")
                            .andInclude("name", "portions", "prepare_time", "max_storage_time", "diet", "ingredients", "steps")
                            .and(ArrayOperators.arrayOf(
                                    SetOperators.SetIntersection.arrayAsSet("ingredients.name")
                                            .intersects(LiteralOperators.Literal.asLiteral(namesProductsToUse))
                            ).length())
                            .as("matchingIngredientsCount"),
                    Aggregation.group("matchingIngredientsCount")
                            .push(Aggregation.ROOT).as("recipes"),
                    Aggregation.sort(Sort.Direction.DESC, "_id"),
                    Aggregation.limit(limit),
                    Aggregation.unwind("$recipes"),
                    Aggregation.replaceRoot("$recipes")
            );
        }
        return new ArrayList<>();
    }

    private Criteria isEmptyDislikedProducts(List<String> dislikedProducts){
        if (!dislikedProducts.isEmpty()) {
            return Criteria.where("ingredients.name").nin(dislikedProducts);
        }
        return new Criteria();
    }

    private Criteria isEmptyDiet(String diet){
        if(!diet.isEmpty()){
            return Criteria.where("diet").is(diet);
        }
        return new Criteria();
    }

    private Criteria isEmptyPrepareTime(int prepareTime){
        if(prepareTime == -1){
            return new Criteria();
        }
        return Criteria.where("prepare_time").lte(prepareTime);
    }
}
