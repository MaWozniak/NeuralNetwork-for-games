package Game;

import Game.Food.Food;
import Game.Food.FoodManager;
import Game.Organisms.GenericPredator;
import Game.Organisms.Predator;
import Game.Organisms.Prey;
import Game.Stage.Stage;
import Game.Stage.StageManager;
import Genetics.Generations;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final ModelView modelView = new ModelView(false);
    private final Generations generations;
    private final GameLoop gameLoop;

    private final List<Prey> AI_prey = new ArrayList<>();
    private final List<Predator> predators = new ArrayList<>();

    private final StageManager stageManager;
    private final FoodManager foodManager = new FoodManager();

    public Game(StageManager stageManager) {
        this.stageManager = stageManager;
        Stage startStage = stageManager.getStage(0);
        addNewPredators(startStage.getNumOfPredators());
        this.generations = new Generations(AI_prey, startStage.getNumOfPreys(), startStage.getPreyMaxAge(), stageManager);
        this.generations.addFirstGeneration();
        gameLoop = new GameLoop(this);
        startGameLoop();
    }

    private void startGameLoop() {
        Thread thread = new Thread(gameLoop);
        thread.start();
    }

    public int getFrameRate() {
        return this.gameLoop.getFramerate();
    }

    public void setFrameRate(int frameRate) {
        this.gameLoop.setMillis(1000 / frameRate);
    }

    void gameLoop() throws IOException {
        foodManager.generateFood(this.getStage());
        organismsMoves();
        modelViewSet();
        scoreAi();
        checkPredators(this.getStage().getNumOfPredators());
        checkGenerations();
        validate();

    }

    private void addNewPredators(int number) {
        for (int i = 0; i < number; i++) {
            int yStartPosChange = 100;
            if (Math.random() > 0.5) {
                yStartPosChange = 700;
            }
            double xStartPos = 100 + 950 * Math.random();
            double yStartPos = yStartPosChange + 20 * Math.random();
            Predator newPredator = new GenericPredator(xStartPos, yStartPos);
            predators.add(newPredator);
        }

    }

    private void checkPredators(int number) {
        predators.removeIf((generatedPredator) -> !generatedPredator.isAlive());
        if (number == 0) {
            predators.clear();
        }
        if (predators.size() < number) {
            addNewPredators(1);
        }
    }

    private void checkGenerations() throws IOException {
        if (AI_prey.size() == 0) {
            generations.addNewGeneration();
        }

    }

    private void scoreAi() {
        for (Prey prey : AI_prey) {
            prey.updateScore();
        }

    }

    private void organismsMoves() {

        for (Predator generatedPredator : predators) {
            generatedPredator.move(modelView.getModel(), this.getStage());

        }

        for (Prey generatedPrey : AI_prey) {
            generatedPrey.move(modelView.getModel(), this.getStage());
            if (generatedPrey.getEnergy() < 0.001) {
                generations.deathPrey(generatedPrey);
            }
            if (generatedPrey.getAge() > generatedPrey.getPreyMaxAge()) {
                generations.deathPrey(generatedPrey);
            }

            for (Food food : foodManager.getFoodPoints()) {
                if (Math.abs((double) food.getX() - generatedPrey.getX()) < 10 && Math.abs((double) food.getY() - generatedPrey.getY()) < 10
                        && generatedPrey.getEnergy() < 130) {
                    generatedPrey.feed();
                    food.eated();
                }
            }

            //simple kill - right now main.Predator see all board and all main.Prey, doesn't has a FIELD OF VIEW
            for (Predator generatedPredator : predators) {
                int radius = 12;
                if ((Math.abs((int) (generatedPredator.getX() - generatedPrey.getX())) < radius)
                        && (Math.abs((int) (generatedPredator.getY() - generatedPrey.getY())) < radius)) {
                    if (generatedPredator.getEnergy() < 1260) {
                        generations.deathPrey(generatedPrey);
                        generatedPredator.eat();
                        // generatedPredator.slowDown();
                    }
                }
            }
        }

        foodManager.getFoodPoints().removeIf(Food::isEaten);

    }

    private void modelViewSet() {
        modelView.clear(this.getStage());

        for (Predator generatedPredator : predators) {
            modelView.set((int) generatedPredator.getX(), (int) generatedPredator.getY(), 'P', 40);
        }

        for (Food food : foodManager.getFoodPoints()) {
            modelView.set(food.getX(), food.getY(), 'F', 8);
        }

        for (Prey generatedPrey : AI_prey) {
            modelView.set((int) generatedPrey.getX(), (int) generatedPrey.getY(), 'X', 12);
        }

        modelView.repaintGui();
    }

    public void paint(Graphics2D g) {
        try {
            predators.forEach(predator -> predator.paint(g));
            foodManager.getFoodPoints().forEach(food -> {
                if (!food.isEaten()) food.paint(g);
            });
            AI_prey.forEach(prey -> prey.paint(g));
        } catch (java.util.ConcurrentModificationException ignored) {

        }

    }

    private void validate() {
        if (predators.size() != 0) {
            for (int i = 0; i < predators.size(); i++) {
                if (!predators.get(i).isAlive()) {
                    predators.remove(i);
                    break;
                }
            }
        }
        if (AI_prey.size() != 0) {
            for (int i = 0; i < AI_prey.size(); i++) {
                if (!AI_prey.get(i).isAlive()) {
                    AI_prey.remove(i);
                    break;
                }
            }
        }
    }

    public List<Double> getGenerationsScores() {
        return this.generations.getGenerationsScoresList();
    }

    public List<Double> getGenerationsAverageScores() {
        return this.generations.getGenerationsAverageList();
    }

    public String getMutationRate() {
        return generations.getMutationRate();
    }

    public Stage getStage() {
        return stageManager.getStage(generations.getCount() - 1);
    }
}
