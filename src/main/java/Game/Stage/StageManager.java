package Game.Stage;

import Game.Food.FoodStrategy;

import java.util.ArrayList;
import java.util.List;

public class StageManager {
    public static List<Stage> problem = new ArrayList<>();

    private final int[] strategy;

    public StageManager(int[] strategy) {
        this.strategy = strategy;

        //learning move from hidden place to rest of the board
        Stage problem1 = new Stage("problem 1");
        problem1.setLearningNetwork(0);
        problem1.setHidePlacesCenter(true);
        problem1.setHidePlacesBorder(true);
        problem1.setHidePlacesPenalty(true);
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
        //next only testing:
        problem4.setNumOfPredators(4);
        //end
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
        problem6.setFoodTypeStrategy(FoodStrategy.FOUR_POINTS);
        //problem6.setFoodTypeStrategy(FoodStrategy.TWO_POINTS);
        problem.add(problem6);
    }

    public Stage getStage(int numOfGeneration) {
        Stage result = null;
        for (int i = 0; i < strategy.length; i++) {
            if (numOfGeneration < strategy[i]) {
                result = problem.get(i);
                break;
            }
        }
        if (result == null) {
            result = problem.get(strategy.length);
        }
        //FORCED FOR TEST
        result = problem.get(3);
        return result;
    }
}
