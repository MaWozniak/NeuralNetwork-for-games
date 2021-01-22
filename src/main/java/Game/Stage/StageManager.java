package Game.Stage;

import java.util.ArrayList;
import java.util.List;

public class StageManager {
    public static List<Stage> problem = new ArrayList<>();

    private final int[] strategy;

    public StageManager(int[] strategy) {
        this.strategy = strategy;

        //learning move from hidden place to rest of the board
        Stage problem1 = new Stage();
        problem1.setHidePlacesCenter(true);
        problem1.setHidePlacesBorder(true);
        problem1.setHidePlacesPenalty(true);
        problem1.setPreyMaxAge(20);
        problem.add(problem1);

        //learning run from Predators to hidePlaces
        Stage problem2 = new Stage();
        problem2.setHidePlacesCenter(true);
        problem2.setHidePlacesBorder(true);
        problem2.setHidePlacesPenalty(true);
        problem2.setNumOfPredators(4);
        problem.add(problem2);

        //learning only to run allover the board
        Stage problem3 = new Stage();
        problem3.setNumOfPredators(5);
        problem.add(problem3);

        //learning to move and find the food
        Stage problem4 = new Stage();
        problem4.setPreyMustEat(true);
        problem4.setFoodTypeStrategy(FoodStrategy.SIMPLE);
        problem.add(problem4);

        //learning to move and find food II
        Stage problem5 = new Stage();
        problem5.setPreyMustEat(true);
        problem5.setFoodTypeStrategy(FoodStrategy.FOUR_POINTS);
        problem.add(problem5);

        //testing problem
        Stage problem6 = new Stage();
        problem6.setNumOfPreys(75);
        problem6.setPreyMaxAge(60);
        problem6.setHidePlacesCenter(true);
        problem6.setHidePlacesPenalty(true);
        problem6.setNumOfPredators(3);
        problem6.setPreyMustEat(true);
        problem6.setFoodTypeStrategy(FoodStrategy.TWO_POINTS);
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
        result = problem.get(0);
        return result;
    }
}
