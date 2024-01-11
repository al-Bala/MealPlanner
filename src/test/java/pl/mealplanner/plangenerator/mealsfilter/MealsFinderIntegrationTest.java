package pl.mealplanner.plangenerator.mealsfilter;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import pl.mealplanner.BaseIntegrationTest;
import pl.mealplanner.plangenerator.mealsfilter.dto.InfoForFiltering;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;
import pl.mealplanner.plangenerator.mealsfilter.entity.Recipe;

import java.util.Collections;
import java.util.List;

public class MealsFinderIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private MealsFilterRepositoryImpl repository;
    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    public void database_test(){
        // given
        InfoForFiltering infoForFiltering = getInfoForFiltering();

        // when
        List<Recipe> foundMeals = repository.findMatchingRecipes(infoForFiltering);
        System.out.println(foundMeals);

        // then
//        List<FilteredRecipeDto> expectedFoundMeals = List.of(
//                FilteredRecipeDto.builder()
//                        .name("Kasza jaglana z warzywami")
//                        .portions(4)
//                        .prepare_time(30)
//                        .max_storage_time(2)
//                        .diet("wegetariańska")
//                        .ingredients(List.of(
//                                new IngredientDto("kasza jaglana", 200, "g"),
//                                new IngredientDto("marchew", 2, "szt"),
//                                new IngredientDto("brokuły", 150, "g"),
//                                new IngredientDto("oliwa z oliwek", 30, "ml")
//                        ))
//                        .build()
//        );
    }

    @NotNull
    private static InfoForFiltering getInfoForFiltering() {
        return InfoForFiltering.builder()
                .forHowManyDays(1)
                .diet("")
                .timeForPrepareMin(-1)
//                .productsToUse(Collections.emptyList())
                .productsToUse(List.of(
                        new IngredientDto("marchew", 100, "g")
//                        new IngredientDto("oliwa z oliwek", 50, "ml"),
//                        new IngredientDto("jajka", 2, "szt")
//                        new IngredientDto("truskawki", 2, "szt")
                ))
                .dislikedProducts(Collections.emptyList())
//                .dislikedProducts(List.of("brokuły"))
                .build();
    }
}
