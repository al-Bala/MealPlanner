package pl.mealplanner.plangenerator.mealscounter;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.mealplanner.plangenerator.domain.dto.DayInfo;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.WeekInfoDto;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
class MealsCounterService {

    private int daysNumber = 0;
    private final List<OneMealInfo> oneMealInfoList = new ArrayList<>();

    List<OneMealInfo> countNumberOfMeals(WeekInfoDto weekInfoDto){
        List<DayInfo> dayInfoList = weekInfoDto.dayInfoList();

        for (int i = dayInfoList.size()-1; i >= 0; i--){
            checkEatingPlan(dayInfoList.get(i));
        }
        // lista informacji o posiłkach do planu - W ODWRÓCONEJ KOLEJNOŚCI
        return oneMealInfoList;
    }

    private void checkEatingPlan(DayInfo dayInfo){
        switch (dayInfo.eatingPlans().id()) {
            case "C01" -> {
                daysNumber++;
                oneMealInfoList.add(
                        OneMealInfo.builder()
                                .dayOfWeek(dayInfo.day())
                                .forHowManyDays(daysNumber)
                                .timeForPrepareMin(dayInfo.eatingPlans().timeMin())
                                .build()
                );
                daysNumber = 0;
            }
            case "B02" -> {
            }
            case "E03" -> {
                daysNumber++;
            }
            default -> log.error("Wrong EatingPlan ID");
        }
    }
}
