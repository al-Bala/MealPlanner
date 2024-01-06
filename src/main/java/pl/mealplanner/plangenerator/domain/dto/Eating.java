package pl.mealplanner.plangenerator.domain.dto;

public class Eating {
    private EatingStatus status;

    public Eating() {
    }

    public Eating(EatingStatus status) {
        this.status = status;
    }

    public enum EatingStatus {
        COOKING("gotuję"),
        NOT_COOKING("nie gotuję"),
        COOKING_BEFORE("gotowałem/am wcześniej");

        String text;

        EatingStatus(String userText) {
            text = userText;
        }
    }
    public void cooking(int timeMin){

    }

    public boolean notCooking(){
        return getStatus() == EatingStatus.NOT_COOKING;
    }

    public void cookingBefore(){

    }

    public void setEatingPlan(EatingStatus status) {
        this.status = status;
    }

    public EatingStatus getStatus() {
        return status;
    }
}
