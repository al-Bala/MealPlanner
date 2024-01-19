package pl.mealplanner.plangenerator.infrastructure.dto;

import lombok.Data;

@Data
public class DislikedProductRequest {
    String name;

    public DislikedProductRequest(){
        this.name = "";
    }
}
