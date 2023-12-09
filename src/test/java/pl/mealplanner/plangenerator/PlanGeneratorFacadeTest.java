package pl.mealplanner.plangenerator;

import org.junit.Test;
import pl.mealplanner.plangenerator.domain.PlanGeneratorConfig;
import pl.mealplanner.plangenerator.domain.PlanGeneratorFacade;
import pl.mealplanner.plangenerator.domain.dto.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class PlanGeneratorFacadeTest {

//    PlanGeneratorConfig config = new PlanGeneratorConfig();
//    @Test
//    public void should_count_nr_of_meals_for_week(){
//        // given
//        PlanGeneratorFacade planGeneratorFacade = config.createForTest();
//
//        WeekInfoDto weekInfo = new WeekInfoDto(List.of(
//                new DayInfo(LocalDate.of(2023, 12,9), new EatingPlans("C01", 30)),
//                new DayInfo(LocalDate.of(2023, 12,10), new EatingPlans("B02", 0)),
//                new DayInfo(LocalDate.of(2023, 12,11), new EatingPlans("E03", 0)),
//                new DayInfo(LocalDate.of(2023, 12,12), new EatingPlans("C01", 60)),
//                new DayInfo(LocalDate.of(2023, 12,13), new EatingPlans("E03", 0)),
//                new DayInfo(LocalDate.of(2023, 12,14), new EatingPlans("C01", 40)),
//                new DayInfo(LocalDate.of(2023, 12,15), new EatingPlans("E03", 0))
//        ));
//        // when
//        MealsInfoDto mealsInfoDto = planGeneratorFacade.generateMealPlanner(weekInfo);
//
//        // then
//        MealsInfoDto expectedMealsInfo = new MealsInfoDto(List.of(
//            new OneMealInfo(LocalDate.of(2023, 12, 14), 2, 40),
//            new OneMealInfo(LocalDate.of(2023, 12, 12), 2, 60),
//            new OneMealInfo(LocalDate.of(2023, 12, 9), 2, 30)
//        ));
//        assertEquals(expectedMealsInfo, mealsInfoDto);
//
//    }

}