package pl.mealplanner.plangenerator.unitconverter;

import lombok.Data;

@Data
public class DisplayCountAU {
    float amountDisplay;
    String unitDisplay;
    float amountCount;
    String unitCount;

    public DisplayCountAU() {
    }

    public DisplayCountAU(float amountDisplay, String unitDisplay, float amountCount,  String unitCount) {
        this.amountDisplay = amountDisplay;
        this.unitDisplay = unitDisplay;
        this.amountCount = amountCount;
        this.unitCount = unitCount;
    }
}
