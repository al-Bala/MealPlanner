package pl.mealplanner.plangenerator.mealsfilter;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.mealplanner.loginandregister.domain.LoginAndRegisterFacade;
import pl.mealplanner.loginandregister.domain.dto.PlanHistoryDto;
import pl.mealplanner.loginandregister.domain.dto.UserPlanHistory;
import pl.mealplanner.plangenerator.mealsfilter.dto.FilteredRecipeDto;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HistoryCheckerTest {
    @Mock
    private LoginAndRegisterFacade loginAndRegisterFacade;

    @InjectMocks
    private HistoryChecker historyChecker;

    @Test
    public void should_return_one_recipe_when_receive_list_of_filtered_recipes(){
        // given
        List<FilteredRecipeDto> recipesToCheck = List.of(
                FilteredRecipeDto.builder()
                        .id(new ObjectId("111111111111111111111111"))
                        .build(),
                FilteredRecipeDto.builder()
                        .id(new ObjectId("222222222222222222222222"))
                        .build(),
                FilteredRecipeDto.builder()
                        .id(new ObjectId("333333333333333333333333"))
                        .build()
        );

        String username = "testUser1";
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<PlanHistoryDto> planHistoryList = List.of(
                new PlanHistoryDto(LocalDate.of(2023, 12, 11), new ObjectId("111111111111111111111111"))
        );

        when(loginAndRegisterFacade.findPlanHistoryByUsername(username)).thenReturn(new UserPlanHistory(planHistoryList));

        // when
        FilteredRecipeDto chosenCheckedRecipe = historyChecker.checkPreviousWeek(recipesToCheck);
        System.out.println(chosenCheckedRecipe);

        // then
        FilteredRecipeDto expectedRecipe = FilteredRecipeDto.builder()
                .id(new ObjectId("222222222222222222222222"))
                .build();

        Assert.assertEquals(expectedRecipe, chosenCheckedRecipe);

        SecurityContextHolder.clearContext();
    }

}
