package pl.mealplanner.plangenerator.leftproductscounter.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class PlanProductInfo {
    private String name;
    private float amountToUse;
    private float packingMeasure;
    private int nrOfPackets;
    private float surplus;
    private String unit;

//    public PlanProductInfo(String name, float amountToUse, String unit) {
//        this.name = name;
//        this.amountToUse = amountToUse;
//        this.unit = unit;
//    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PlanProductInfo)) {
            return false;
        }
        PlanProductInfo otherIng = (PlanProductInfo) other;
        if (otherIng.name.equals(this.name) && otherIng.unit.equals(this.unit)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit);
    }
}
