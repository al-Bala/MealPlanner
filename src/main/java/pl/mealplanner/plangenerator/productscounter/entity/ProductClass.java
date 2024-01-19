package pl.mealplanner.plangenerator.productscounter.entity;

import lombok.Data;
import pl.mealplanner.plangenerator.productscounter.entity.PackingMeasures;

import java.util.List;

@Data
public class ProductClass {
    String name;
    List<PackingMeasures> packingMeasures;
}
