package pl.mealplanner.plangenerator.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import pl.mealplanner.plangenerator.mealsfilter.dto.DislikedProductDto;
import pl.mealplanner.plangenerator.mealsfilter.dto.IngredientDto;

import java.util.List;

@Builder
public record UserPreferencesRequest(
        int numberOfPortions,
        String diet,
        List<IngredientDto> productsToUse,
        List<DislikedProductDto> dislikedProducts
) {
    public UserPreferencesRequest(){
        this(1, "", List.of(new IngredientDto()), List.of(new DislikedProductDto()));
    }
}
