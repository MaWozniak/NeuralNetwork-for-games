package Game.Food;

import Game.Stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FoodManager {

    private final List<Food> foodPoints = new ArrayList();
    private final int food = 100;
    private final int foodMin = 15;

    public void generateFood(Stage stage) {
        if (stage.getFoodTypeStrategy() == FoodStrategy.NONE) {
            foodPoints.clear();
        } else if (stage.getFoodTypeStrategy() == FoodStrategy.SIMPLE) {
            if (this.foodPoints.size() <= this.foodMin) {
                for (int i = 0; i < this.food - this.foodMin; ++i) {
                    int xStartPos = 100 + (int) (1000 * Math.random());
                    int yStartPos = 100 + (int) (600 * Math.random());
                    Food newFood = new Food(xStartPos, yStartPos);
                    this.foodPoints.add(newFood);
                }
            }
        } else if (stage.getFoodTypeStrategy() == FoodStrategy.FOUR_POINTS) {
            if (this.foodPoints.size() <= this.foodMin) {
                for (int i = 0; i < this.food - this.foodMin; ++i) {
                    int xStartPosChange = 100;
                    int yStartPosChange = 100;
                    if (Math.random() > 0.5) {
                        xStartPosChange = 900;
                    }
                    if (Math.random() > 0.5) {
                        yStartPosChange = 600;
                    }
                    int xStartPos = xStartPosChange + (int) (150 * Math.random());
                    int yStartPos = yStartPosChange + (int) (150 * Math.random());
                    Food newFood = new Food(xStartPos, yStartPos);
                    this.foodPoints.add(newFood);
                }
            }
        } else if (stage.getFoodTypeStrategy() == FoodStrategy.TWO_POINTS) {
            if (this.foodPoints.size() <= this.foodMin) {
                for (int i = 0; i < this.food - this.foodMin; ++i) {
                    int xStartPosChange = 100;
                    if (Math.random() > 0.5) {
                        xStartPosChange = 1000;
                    }
                    int xStartPos = xStartPosChange + (int) (120 * Math.random());
                    int yStartPos = 400 + (int) (120 * Math.random());
                    Food newFood = new Food(xStartPos, yStartPos);
                    this.foodPoints.add(newFood);
                }
            }
        }

    }

    public List<Food> getFoodPoints() {
        return foodPoints;
    }

    public int getFood() {
        return food;
    }

    public int getFoodMin() {
        return foodMin;
    }
}
