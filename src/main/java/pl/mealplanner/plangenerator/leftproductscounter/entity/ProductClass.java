package pl.mealplanner.plangenerator.leftproductscounter.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProductClass {
    String name;
    List<PackingMeasures> packingMeasures;
}
