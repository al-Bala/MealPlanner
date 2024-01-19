package pl.mealplanner.plangenerator.unitconverter;

import lombok.Builder;

@Builder
record AmountAndUnit(
        float amount,
        String unit
) {
}
