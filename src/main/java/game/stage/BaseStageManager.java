package game.stage;

import game.food.FoodStrategy;

import java.util.LinkedList;
import java.util.List;

public class BaseStageManager implements StageManager {
    public static List<Stage> problem = new LinkedList<>();
    private final int multiplier;

    public BaseStageManager(int multiplier) {
        this.multiplier = multiplier;

        //learning move from hidden place to rest of the board
        Stage problem1 = new Stage("problem 1");
        problem1.setLearningNetwork(0);
        problem1.setHidePlacesCenter(true);
        problem1.setHidePlacesBorder(true);
        problem1.setHidePlacesPenalty(true);
        problem1.setFoodTypeStrategy(FoodStrategy.SIMPLE);
        //problem1.setPreyMaxAge(20);
        problem.add(problem1);

        //learning only to run allover the board
        Stage problem2 = new Stage("problem 2");
        problem2.setLearningNetwork(1);
        problem2.setNumOfPredators(3);
        problem.add(problem2);

        //learning run from Predators to hidePlaces
        Stage problem3 = new Stage("problem 3");
        problem3.setLearningNetwork(1);
        problem3.setHidePlacesCenter(true);
        problem3.setHidePlacesBorder(true);
        problem3.setHidePlacesPenalty(true);
        problem3.setNumOfPredators(3);
        problem.add(problem3);

        //learning to move and find the food
        Stage problem4 = new Stage("problem 4");
        problem4.setLearningNetwork(2);
        problem4.setPreyMustEat(true);
        problem4.setFoodTypeStrategy(FoodStrategy.SIMPLE);
        problem.add(problem4);

        //learning to move and find food II
        Stage problem5 = new Stage("problem 5");
        problem5.setLearningNetwork(2);
        problem5.setPreyMustEat(true);
        problem5.setFoodTypeStrategy(FoodStrategy.FOUR_POINTS);
        problem.add(problem5);

        //testing problem
        Stage problem6 = new Stage("problem 6 - final");
        problem6.setLearningNetwork(-1); //bridge network
        problem6.setNumOfPreys(75);
        problem6.setHidePlacesCenter(true);
        problem6.setHidePlacesPenalty(false);
        problem6.setNumOfPredators(4);
        problem6.setPreyMustEat(true);
        //problem6.setFoodTypeStrategy(FoodStrategy.FOUR_POINTS);
        problem6.setFoodTypeStrategy(FoodStrategy.TWO_POINTS);
        problem.add(problem6);
    }

    @Override
    public Stage getStage(int numOfGeneration) {
        int[] STRATEGY = {2 * multiplier, 6 * multiplier, 10 * multiplier, 14 * multiplier, 18 * multiplier};
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
