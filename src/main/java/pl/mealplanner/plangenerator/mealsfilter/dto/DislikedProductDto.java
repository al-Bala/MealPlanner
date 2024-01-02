package pl.mealplanner.plangenerator.mealsfilter.dto;

public record DislikedProductDto(
        String name
) {
    public  DislikedProductDto(){
        this("");
    }
}
