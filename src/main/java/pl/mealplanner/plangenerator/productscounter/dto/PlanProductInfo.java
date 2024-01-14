package pl.mealplanner.plangenerator.productscounter.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class PlanProductInfo {
    private String name;
    private float amountToUseCount;
    private float packingMeasure;
    private int nrOfPackets;
    private float surplus;
    private String unitCount;

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
        if (otherIng.name.equals(this.name) && otherIng.unitCount.equals(this.unitCount)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unitCount);
    }
}
