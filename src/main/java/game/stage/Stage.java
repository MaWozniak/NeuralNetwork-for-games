package game.stage;

import game.food.FoodStrategy;

public class Stage {
    private String name = "STAGE-NN";
    private int learningNetwork = 0;
    private int numOfPreys = 50;
    private int preyMaxAge = 80;
    private int numOfPredators = 0;
    private boolean isHidePlacesCenter = false;
    private boolean isHidePlacesBorder = false;
    private boolean hidePlacesPenalty = false;
    private boolean isPreyMustEat = false;
    private FoodStrategy foodTypeStrategy = FoodStrategy.NONE;

    public Stage(String name) {
        this.name = name;
    }

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

    public int getNumOfPreys() {
        return numOfPreys;
    }

    public void setNumOfPreys(int numOfPreys) {
        this.numOfPreys = numOfPreys;
    }

    public int getPreyMaxAge() {
        return preyMaxAge;
    }

    public void setPreyMaxAge(int preyMaxAge) {
        this.preyMaxAge = preyMaxAge;
    }

    public int getLearningNetwork() {
        return learningNetwork;
    }

    public void setLearningNetwork(int learningNetwork) {
        this.learningNetwork = learningNetwork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
