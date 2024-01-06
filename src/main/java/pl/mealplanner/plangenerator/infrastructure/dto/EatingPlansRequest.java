package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Data;

@Data
public class EatingPlansRequest {
    String id;
    int timeMin;

    public EatingPlansRequest() {
        this.timeMin = -1;
    }
}
