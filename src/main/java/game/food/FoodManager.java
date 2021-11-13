package game.food;

import game.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class FoodManager {

    private final List<Food> foodPoints = new LinkedList<>();
    private static final int FOOD_SIZE = 120;
    private static final int FOOD_MIN = 1;

    public void generateFood(Stage stage) {
        foodPointsLifeSpan();

        switch (stage.getFoodTypeStrategy()) {
            case NONE:
                foodPoints.clear();
            case SIMPLE:
                simpleFoodStrategy();
            case FOUR_POINTS:
                fourPointsStrategy();
            case TWO_POINTS:
                twoPointsStrategy();
            case CHANGING_PLACE:
                changingPlaceStrategy();
            case CHANGING_AREA:
                changingAreaStrategy();
        }
    }

    private void foodPointsLifeSpan() {
        foodPoints.forEach(x->x.lifeSpan--);
        for (Food foodPoint : foodPoints) {
            if (foodPoint.lifeSpan == 0) {
                foodPoint.eated();
            }
        }
    }

    private void simpleFoodStrategy() {
        if (isBelowMinimum()) {
            for (int i = 0; i < FOOD_SIZE - FOOD_MIN; ++i) {
                int xStartPos = 100 + (int) (1000 * Math.random());
                int yStartPos = 100 + (int) (600 * Math.random());
                Food newFood = new Food(xStartPos, yStartPos);
                this.foodPoints.add(newFood);
            }
        }
    }

    private void fourPointsStrategy() {
        if (isBelowMinimum()) {
            for (int i = 0; i < FOOD_SIZE - FOOD_MIN; ++i) {
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
    }

    private void twoPointsStrategy() {
        if (isBelowMinimum()) {
            for (int i = 0; i < FOOD_SIZE - FOOD_MIN; ++i) {
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

    private void changingPlaceStrategy() {
        int x = 100 + (int) (900 * Math.random());
        int y = 50 + (int) (500 * Math.random());

        if (isBelowMinimum()) {
            foodPoints.clear();
            for (int i = 0; i < FOOD_SIZE; i++) {
                int radius = 20;
                x += (int) (Math.random() * radius) - radius / 2;
                y += (int) (Math.random() * radius) - radius / 2;
                Food newFood = new Food(x, y);
                this.foodPoints.add(newFood);
            }
        }
    }

    private void changingAreaStrategy() {
        int x = 250;
        int y = 150;
        if (Math.random() > 0.5) {
            x = 900;
        }
        if (Math.random() > 0.5) {
            y = 600;
        }

        if (isBelowMinimum()) {
            foodPoints.clear();
            for (int i = 0; i < FOOD_SIZE; i++) {
                int radius = 130;
                x += (int) (Math.random() * radius) - radius / 2;
                y += (int) (Math.random() * radius) - radius / 2;
                Food newFood = new Food(x, y);
                this.foodPoints.add(newFood);
            }
        }
    }

    private boolean isBelowMinimum() {
        return this.foodPoints.size() <= FOOD_MIN;
    }

    public List<Food> getFoodPoints() {
        return foodPoints;
    }
}
