package pl.mealplanner.plangenerator.mealscounter;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.mealplanner.plangenerator.domain.dto.DayInfo;
import pl.mealplanner.plangenerator.domain.dto.OneMealInfo;
import pl.mealplanner.plangenerator.domain.dto.WeekInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
class MealsCounterService {

    private int daysNumber = 0;
    private final List<OneMealInfo> oneMealInfoList = new ArrayList<>();

    List<OneMealInfo> countNumberOfMeals(WeekInfo weekInfo){
        oneMealInfoList.clear();
        List<DayInfo> dayInfoList = weekInfo.dayInfoList();

        for (int i = dayInfoList.size()-1; i >= 0; i--){
            checkEatingPlan(dayInfoList.get(i));
        }
        Collections.reverse(oneMealInfoList);
        return oneMealInfoList;
    }

    private void checkEatingPlan(DayInfo dayInfo){
        switch (dayInfo.eatingPlan().id()) {
            case "C01" -> {
                daysNumber++;
                addToList(dayInfo, daysNumber, dayInfo.eatingPlan().timeMin());
                daysNumber = 0;
            }
            case "B02" -> {
                addToList(dayInfo, 0, -1);
            }
            case "E03" -> {
                addToList(dayInfo, 0, -1);

                daysNumber++;
            }
            default -> log.error("Wrong EatingPlan ID");
        }
    }

    private void addToList(DayInfo dayInfo, int forHowManyDays, int timeForPrepareMin) {
        oneMealInfoList.add(
                OneMealInfo.builder()
                        .dayOfWeek(dayInfo.day())
                        .forHowManyDays(forHowManyDays)
                        .timeForPrepareMin(timeForPrepareMin)
                        .build());
    }
}
