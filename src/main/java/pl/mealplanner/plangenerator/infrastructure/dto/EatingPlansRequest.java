package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EatingPlansRequest {
    String id;
    int timeMin;

    public EatingPlansRequest(String id, int timeMin) {
        this.id = id;
        this.timeMin = timeMin;
    }

    public EatingPlansRequest() {
        this.timeMin = -1;
    }
}
