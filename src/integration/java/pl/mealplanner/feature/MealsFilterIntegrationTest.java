package pl.mealplanner.feature;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import pl.mealplanner.BaseIntegrationTest;
import pl.mealplanner.plangenerator.domain.dto.InfoForMealsSearch;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.UserPreferencesDto;
import pl.mealplanner.plangenerator.mealsfilter.MealsFilterFacade;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.ProductDto;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealsFilterIntegrationTest extends BaseIntegrationTest {
    @Autowired
    public MealsFilterFacade mealsFilterFacade;
    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    public void database_test(){
        // given
        InfoForMealsSearch infoForMealsSearch = getInfoForMealsSearch();

        // when
        List<FilteredRecipeDto> foundMeals = mealsFilterFacade.findMeals(infoForMealsSearch);

        // then
        List<FilteredRecipeDto> expectedFoundMeals = List.of(
                FilteredRecipeDto.builder()
                        .name("Kasza jaglana z warzywami")
                        .portions(4)
                        .prepare_time(30)
                        .max_storage_time(2)
                        .diet("wegetariańska")
                        .ingredients(List.of(
                                new ProductDto("kasza jaglana", 200, "g"),
                                new ProductDto("marchew", 2, "szt"),
                                new ProductDto("brokuły", 150, "g"),
                                new ProductDto("oliwa z oliwek", 30, "ml")
                        ))
                        .build()
        );

        assertEquals(expectedFoundMeals, foundMeals);
    }

    @NotNull
    private static InfoForMealsSearch getInfoForMealsSearch() {
        UserPreferencesDto preferences = UserPreferencesDto.builder()
                .numberOfPortions(4)
                .diet("wegetariańska")
                .productsToUse(List.of("marchew", "brokuły"))
                .dislikedProducts(List.of("sos sojowy"))
                .build();
        List<OneMealInfo> oneMealInfoList = List.of(
                new OneMealInfo(LocalDate.of(2023, 12,9), 1, 30));
        return new InfoForMealsSearch(oneMealInfoList, preferences);
    }
}
