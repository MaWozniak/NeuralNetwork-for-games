package Game;

import Game.Food.Food;
import Game.Organisms.Predator;
import Game.Organisms.Prey;
import Genetics.Generations;
import lombok.Data;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private final Generations generations;
    private final ModelView model;
    private final List<Prey> AI_prey = new ArrayList<>();
    private final List<Predator> predators = new ArrayList<>();
    private final List<Food> foodPoints = new ArrayList();
    private int predatorsNumber;
    private final GameLoop gameLoop;
    private final boolean hiddenPlaces;
    private final boolean preyAiEnergyCost;
    private final boolean preyForcedMove;
    private final boolean predatorsEnergyCost;
    private final boolean preyAging;
    private final double preyMaxAge;
    private final int food;
    private final int foodMin;

    public Game(int numPredators, int numAIPrey, ModelView model,
                boolean hiddenPlaces, boolean predatorsEnergyCost, boolean preyAiEnergyCost, boolean preyForcedMove, boolean preyAging,
                double preyMaxAge, int food, int foodMin) {
        this.model = model;
        this.preyAiEnergyCost = preyAiEnergyCost;
        this.preyAging = preyAging;
        this.preyMaxAge = preyMaxAge;
        this.preyForcedMove = preyForcedMove;
        this.generations = new Generations(AI_prey, numAIPrey, preyAiEnergyCost, preyForcedMove, preyAging);
        this.generations.addFirstGeneration();
        this.hiddenPlaces = hiddenPlaces;
        this.predatorsEnergyCost = predatorsEnergyCost;
        this.predatorsNumber = numPredators;
        this.addNewPredators(numPredators);
        this.food = food;
        this.foodMin = foodMin;
        Thread thread = new Thread(gameLoop = new GameLoop(this));
        thread.start();

    }

    public int getFramerate() {
        return this.gameLoop.getFramerate();
    }

    public void setFramerate(int framerate) {
        this.gameLoop.setMillis(1000 / framerate);
    }

    void gameLoop() throws IOException {
        this.generateFood();
        organismsMoves();
        modelViewSet();
        scoreAi();
        checkPredators(predatorsNumber);
        checkGenerations();
        validate();

    }

    private void generateFood() {
        if (this.foodPoints.size() <= this.foodMin) {
            for (int i = 0; i < this.food - this.foodMin; ++i) {
                int xStartPosChange = 100;
                int yStartPosChange = 100;
                if (Math.random() > 0.5) {
                    xStartPosChange = 1000;
                }
                int xStartPos = xStartPosChange + (int) (120 * Math.random());
                int yStartPos = 400 + (int) (120 * Math.random());

                //change in time
                int firstFaze = 30; //200
                int secondFaze = 1500; //600
                if (this.generations.getCount() < firstFaze) {
                    xStartPos = 100 + (int) (1000 * Math.random());
                    yStartPos = 100 + (int) (600 * Math.random());
                }
                if (this.generations.getCount() >= firstFaze && this.generations.getCount() < secondFaze) {
                    if (Math.random() > 0.5) {
                        xStartPosChange = 900;
                    }
                    if (Math.random() > 0.5) {
                        yStartPosChange = 600;
                    }
                    xStartPos = xStartPosChange + (int) (150 * Math.random());
                    yStartPos = yStartPosChange + (int) (150 * Math.random());
                }
//                if(this.generations.getCount() < 40 && this.generations.getCount() >= 20) {
//                    xStartPos = 200 + (int) (500 * Math.random());
//                    yStartPos = 200 + (int) (300 * Math.random());
//                }

                Food newFood = new Food(xStartPos, yStartPos);
                this.foodPoints.add(newFood);
            }
        }

    }

    private void addNewPredators(int number) {
        for (int i = 0; i < number; i++) {
            int yStartPosChange = 100;
            if (Math.random() > 0.5) {
                yStartPosChange = 700;
            }
            double xStartPos = 600 + 50 * Math.random();
            double yStartPos = yStartPosChange + 20 * Math.random();
            Predator newPredator = new Predator(xStartPos, yStartPos, this.hiddenPlaces, this.predatorsEnergyCost);
            predators.add(newPredator);
        }

    }

    private void checkPredators(int number) {
        this.predators.removeIf((generatedPredator) -> !generatedPredator.isAlive());
        if (this.predators.size() < number) {
            this.addNewPredators(1);
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
            generatedPredator.move(model.getModel());
        }

        for (Prey generatedPrey : AI_prey) {
            generatedPrey.move(model.getModel());
            if (generatedPrey.getEnergy() < 0.001) {
                generations.deathPrey(generatedPrey);
            }
            if (generatedPrey.getAge() > preyMaxAge) {
                generations.deathPrey(generatedPrey);
            }

            for (Food food : foodPoints) {
                if (Math.abs((double) food.getX() - generatedPrey.getX()) < 10 && Math.abs((double) food.getY() - generatedPrey.getY()) < 10
                        && generatedPrey.getEnergy() < 130) {
                    generatedPrey.feed();
                    food.eated();
                }
            }

            //simple kill - right now main.Predator see all board and all main.Prey, doesn't has a FIELD OF VIEW
            for (Predator generatedPredator : predators) {
                if ((Math.abs((int) (generatedPredator.getX() - generatedPrey.getX())) < 10) && (Math.abs((int) (generatedPredator.getY() - generatedPrey.getY())) < 10)) {
                    if (generatedPredator.getEnergy() < 260) {
                        generations.deathPrey(generatedPrey);
                        generatedPredator.eat();
                    }
                }
            }
        }

        this.foodPoints.removeIf(Food::isEaten);

    }

    private void modelViewSet() {
        model.clear();

        for (Predator generatedPredator : predators) {
            model.set((int) generatedPredator.getX(), (int) generatedPredator.getY(), 'P', 40);
        }

        for (Food food : foodPoints) {
            model.set(food.getX(), food.getY(), 'F', 8);
        }

        for (Prey generatedPrey : AI_prey) {
            model.set((int) generatedPrey.getX(), (int) generatedPrey.getY(), 'X', 12);
        }

        model.repaintGui();
    }

    public void paint(Graphics2D g) {

        try {
            for (Predator generatedPredator : predators) {
                generatedPredator.paint(g);
            }

            for (Food food : foodPoints) {
                if (!food.isEaten()) {
                    food.paint(g);
                }
            }

            for (Prey generatedPrey : AI_prey) {
                generatedPrey.paint(g);
            }

        } catch (java.util.ConcurrentModificationException e) {
            //System.out.println("error: java.util.ConcurrentModificationException");
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

    public int getPredatorsNumber() {
        return predatorsNumber;
    }

    public void addPredator() {
        predatorsNumber += 1;
    }

    public void removePredator() {
        if (predatorsNumber > 0) {
            predatorsNumber -= 1;
        }
    }

    public String getMutationRate() {
        return generations.getMutationRate();
    }

}
