package game.stage;

import game.food.FoodStrategy;

public class FixedStageManager implements StageManager {
    private final Stage testStage = new Stage("fixed test stage");

    public FixedStageManager() {
        testStage.setLearningNetwork(2);
        testStage.setHidePlacesBorder(true);
        testStage.setHidePlacesCenter(true);
        testStage.setHidePlacesPenalty(true);
        testStage.setPreyMustEat(true);
        testStage.setNumOfPredators(4);
        testStage.setFoodTypeStrategy(FoodStrategy.FOUR_POINTS);
    }

    @Override
    public Stage getStage(int numOfGeneration) {
        return testStage;
    }
}
