package game.stage;

import game.food.FoodStrategy;

import java.util.LinkedList;
import java.util.List;

public class FoodLearningStageManager implements StageManager {
    public static List<Stage> problem = new LinkedList<>();

    private static final int MULTIPLIER = 100;
    private static final int[] STRATEGY = {2 * MULTIPLIER, 4 * MULTIPLIER, 8 * MULTIPLIER};

    public FoodLearningStageManager() {

        Stage problem1 = new Stage("problem 1");
        problem1.setFoodTypeStrategy(FoodStrategy.SIMPLE);
        problem1.setPreyMustEat(true);
        problem1.setLearningNetwork(2);
        problem.add(problem1);

        Stage problem2 = new Stage("problem 2");

        problem2.setFoodTypeStrategy(FoodStrategy.CHANGING_AREA);
        problem2.setPreyMustEat(true);
        problem2.setLearningNetwork(2);
        problem.add(problem2);

        Stage problem3 = new Stage("problem 3");
        problem3.setFoodTypeStrategy(FoodStrategy.CHANGING_PLACE);
        problem3.setPreyMustEat(true);
        problem3.setLearningNetwork(2);
        problem.add(problem3);

        Stage problem4 = new Stage("problem 4");
        problem4.setFoodTypeStrategy(FoodStrategy.CHANGING_PLACE);
        problem4.setPreyMustEat(true);
        problem4.setLearningNetwork(2);
        problem4.setNumOfPredators(3);
        problem.add(problem4);

    }

    @Override
    public Stage getStage(int numOfGeneration) {
        Stage result = null;
        for (int i = 0; i < STRATEGY.length; i++) {
            if (numOfGeneration < STRATEGY[i]) {
                result = problem.get(i);
                break;
            }
        }
        if (result == null) {
            result = problem.get(STRATEGY.length);
        }
        return result;
    }
}
