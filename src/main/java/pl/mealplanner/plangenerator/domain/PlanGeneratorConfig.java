package pl.mealplanner.plangenerator.domain;

public class PlanGeneratorConfig {
    public PlanGeneratorFacade createForTest() {
        return new PlanGeneratorFacade(new MealsCounter());
    }
}
