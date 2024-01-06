package pl.mealplanner.plangenerator.mealscounter;

import org.junit.Test;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;

import java.time.LocalDate;
import java.util.List;

public class MealsPackingChooserServiceFacadeTest {
    MealsCounterConfig config = new MealsCounterConfig();
    @Test
    public void should_count_nr_of_meals_for_week(){
        // given
        MealsCounterFacade mealsCounterFacade = config.createForTest();

//        WeekInfoRequest weekInfo = new WeekInfoRequest(List.of(
//                new DayInfo(LocalDate.of(2023, 12,9), new EatingPlans("C01", 30)),
//                new DayInfo(LocalDate.of(2023, 12,10), new EatingPlans("B02", 0)),
//                new DayInfo(LocalDate.of(2023, 12,11), new EatingPlans("E03", 0)),
//                new DayInfo(LocalDate.of(2023, 12,12), new EatingPlans("C01", 60)),
//                new DayInfo(LocalDate.of(2023, 12,13), new EatingPlans("E03", 0)),
//                new DayInfo(LocalDate.of(2023, 12,14), new EatingPlans("C01", 40)),
//                new DayInfo(LocalDate.of(2023, 12,15), new EatingPlans("E03", 0))
//        ));
        // when
//        List<OneMealInfo> mealsInfoList = mealsCounterFacade.countNumberOfMeals(weekInfo);

        // then
        List<OneMealInfo> expectedMealsInfo = List.of(
                new OneMealInfo(LocalDate.of(2023, 12, 14), 2, 40),
                new OneMealInfo(LocalDate.of(2023, 12, 12), 2, 60),
                new OneMealInfo(LocalDate.of(2023, 12, 9), 2, 30)
        );
//        assertEquals(expectedMealsInfo, mealsInfoList);
    }
}