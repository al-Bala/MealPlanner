package pl.mealplanner.feature;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import pl.mealplanner.BaseIntegrationTest;
import pl.mealplanner.plangenerator.mealsfilter.MealsFilterFacade;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.dto.MatchingRecipe;
import pl.mealplanner.plangenerator.productscounter.dto.PlanProductInfo;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MealsFilterIntegrationTest extends BaseIntegrationTest {
    @Autowired
    public MealsFilterFacade mealsFilterFacade;
    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    public void database_test(){
        // given
        InfoForFiltering infoForFiltering = getInfoForFiltering();

        // when
        MatchingRecipe foundMeal = mealsFilterFacade.findRecipe(infoForFiltering);
        System.out.println(foundMeal);

        // then
//        List<ConvertedRecipe> expectedFoundMeals = List.of(
//                ConvertedRecipe.builder()
//                        .name("Kasza jaglana z warzywami")
//                        .portions(4)
//                        .prepare_time(30)
//                        .max_storage_time(2)
//                        .diet("wegetariańska")
//                        .ingredients(List.of(
//                                new IngredientConverted("kasza jaglana",
//                                        List.of(new AmountAndUnit(200, "g"), new AmountAndUnit(200, "g"))),
//                                new IngredientConverted("marchew",
//                                        List.of(new AmountAndUnit(2, "szt"), new AmountAndUnit(200, "g")))
////                                new IngredientDto("marchew", 2, "szt"),
////                                new IngredientDto("brokuły", 150, "g"),
////                                new IngredientDto("oliwa z oliwek", 30, "ml")
//                        ))
//                        .build()
//        );

//        assertEquals(expectedFoundMeals, foundMeals);
    }

    @NotNull
    private static InfoForFiltering getInfoForFiltering() {
        return InfoForFiltering.builder()
                .forHowManyDays(1)
                .diet("")
                .timeForPrepareMin(30)
                .productsToUse(List.of("marchew"))
                .dislikedProducts(Collections.emptyList())
                .build();


//        UserPreferencesDto preferences = UserPreferencesDto.builder()
//                .numberOfPortions(4)
//                .diet("wegetariańska")
//                .productsToUse(List.of(
//                        new IngredientDto("marchew", 100, "g"),
//                        new IngredientDto("brokuły", 1, "szt")
//                ))
//                .dislikedProducts(List.of("sos sojowy"))
//                .build();
//        List<OneMealInfo> oneMealInfoList = List.of(
//                new OneMealInfo(LocalDate.of(2023, 12,9), 1, 30));
//        return new InfoForMealsSearch(oneMealInfoList, preferences);
    }
}
