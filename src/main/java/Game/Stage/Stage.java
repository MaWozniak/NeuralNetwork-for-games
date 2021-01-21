package Game.Stage;

public class Stage {
    private int numOfPredators = 0;
    private boolean isHidePlacesCenter = false;
    private boolean isHidePlacesBorder = false;
    private boolean hidePlacesPenalty = false;
    private boolean isPreyMustEat = false;
    private FoodStrategy foodTypeStrategy = FoodStrategy.NONE;

    public int getNumOfPredators() {
        return numOfPredators;
    }

    public void setNumOfPredators(int numOfPredators) {
        this.numOfPredators = numOfPredators;
    }

    public boolean isHidePlacesCenter() {
        return isHidePlacesCenter;
    }

    public void setHidePlacesCenter(boolean hidePlacesCenter) {
        isHidePlacesCenter = hidePlacesCenter;
    }

    public boolean isHidePlacesBorder() {
        return isHidePlacesBorder;
    }

    public void setHidePlacesBorder(boolean hidePlacesBorder) {
        isHidePlacesBorder = hidePlacesBorder;
    }

    public boolean isHidePlacesPenalty() {
        return hidePlacesPenalty;
    }

    public void setHidePlacesPenalty(boolean hidePlacesPenalty) {
        this.hidePlacesPenalty = hidePlacesPenalty;
    }

    public boolean isPreyMustEat() {
        return isPreyMustEat;
    }

    public void setPreyMustEat(boolean preyMustEat) {
        isPreyMustEat = preyMustEat;
    }

    public FoodStrategy getFoodTypeStrategy() {
        return foodTypeStrategy;
    }

    public void setFoodTypeStrategy(FoodStrategy foodTypeStrategy) {
        this.foodTypeStrategy = foodTypeStrategy;
    }
}