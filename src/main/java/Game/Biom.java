package Game;

import Genetics.Generations;
import lombok.Data;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class Biom {
    private final Generations generations;
    private final ModelView model;
    private final int numPrey;
    private final List<Prey> prey = new ArrayList<>();
    private final List<PreyAI> AI_prey = new ArrayList<>();
    private final List<Predator> predators = new ArrayList<>();
    private final List<Food> foodPoints = new ArrayList();
    private int predatorsNumber;
    private final LifecycleThread lifecycleThread;
    private final int organismUpdateFramerate;
    private final boolean hiddenPlaces;
    private final boolean preyAiEnergyCost;
    private final boolean preyForcedMove;
    private final boolean predatorsEnergyCost;
    private final boolean preyAging;
    private final double preyMaxAge;
    private final int food;
    private final int foodMin;

    public Biom(int resetBucket, int numPrey, int numPredators, int numAIPrey, int frameRate, boolean fullSpeed, ModelView model, int organismUpdateFrameRate,
                boolean hiddenPlaces, boolean predatorsEnergyCost, boolean preyAiEnergyCost, boolean preyForcedMove, boolean preyAging,
                double preyMaxAge, int food, int foodMin) {
        this.model = model;
        this.organismUpdateFramerate = organismUpdateFrameRate;
        this.preyAiEnergyCost = preyAiEnergyCost;
        this.preyAging = preyAging;
        this.preyMaxAge = preyMaxAge;
        this.preyForcedMove = preyForcedMove;
        this.numPrey = numPrey;
        this.addNewPreys(numPrey);
        this.generations = new Generations(AI_prey, numAIPrey, resetBucket, preyAiEnergyCost, preyForcedMove, preyAging);
        this.generations.addFirstGeneration();
        this.hiddenPlaces = hiddenPlaces;
        this.predatorsEnergyCost = predatorsEnergyCost;
        this.predatorsNumber = numPredators;
        this.addNewPredators(numPredators);
        this.food = food;
        this.foodMin = foodMin;
        Thread thread = new Thread(lifecycleThread = new LifecycleThread(frameRate, fullSpeed, this));
        thread.start();

    }

    public int getFramerate() {
        return this.lifecycleThread.getFramerate();
    }

    public void setFramerate(int framerate) {
        this.lifecycleThread.setMillis(1000 / framerate);
    }

    void lifecycle() throws IOException {
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

    private void addNewPreys(int number) {
        for (int i = 0; i < number; i++) {
            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();
            Prey newPrey = new Prey(xStartPos, yStartPos, organismUpdateFramerate);
            prey.add(newPrey);
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
            Predator newPredator = new Predator(xStartPos, yStartPos, organismUpdateFramerate, this.hiddenPlaces, this.predatorsEnergyCost);
            predators.add(newPredator);
        }

    }

    private void checkPredators(int number) {
        this.predators.removeIf((generatedPredator) -> !generatedPredator.isAlive);
        if (this.predators.size() < number) {
            this.addNewPredators(1);
        }

    }

    private void checkGenerations() throws IOException {
        if (AI_prey.size() == 0) {
            generations.addNewGeneration();
        }

        if (prey.size() == 0) {
            addNewPreys(numPrey);
        }
    }

    private void scoreAi() {
        for (PreyAI preyAI : AI_prey) {
            preyAI.updateScore();
        }

    }

    private void addNewAIPreys(int number) {
        for (int i = 0; i < number; i++) {
            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();
            PreyAI newPreyAI = new PreyAI(xStartPos, yStartPos, preyAiEnergyCost, preyForcedMove, preyAging);
            AI_prey.add(newPreyAI);
        }

    }

    private void organismsMoves() {

        for (Predator generatedPredator : predators) {
            generatedPredator.move(model.getModel());
        }

        for (Prey generatedPrey : prey) {
            generatedPrey.move(model.getModel());

            //simple kill - right now main.Predator see all board and all main.Prey, doesn't has a FIELD OF VIEW
            for (Predator generatedPredator : predators) {
                if ((Math.abs((int) (generatedPredator.getX() - generatedPrey.getX())) < 10) && (Math.abs((int) (generatedPredator.getY() - generatedPrey.getY())) < 10)) {
                    if (generatedPredator.getEnergy() < 260) {
                        generatedPrey.isDead();
                        generatedPredator.eat();
                    }
                }
            }
        }

        for (PreyAI generatedPreyAI : AI_prey) {
            generatedPreyAI.move(model.getModel());
            if (generatedPreyAI.getEnergy() < 0.001) {
                generations.deathPrey(generatedPreyAI);
            }
            if (generatedPreyAI.getAge() > preyMaxAge) {
                generations.deathPrey(generatedPreyAI);
            }

            for (Food food : foodPoints) {
                if (Math.abs((double) food.getX() - generatedPreyAI.getX()) < 10 && Math.abs((double) food.getY() - generatedPreyAI.getY()) < 10
                        && generatedPreyAI.getEnergy() < 130) {
                    generatedPreyAI.feed();
                    food.eated();
                }
            }

            //simple kill - right now main.Predator see all board and all main.Prey, doesn't has a FIELD OF VIEW
            for (Predator generatedPredator : predators) {
                if ((Math.abs((int) (generatedPredator.getX() - generatedPreyAI.getX())) < 10) && (Math.abs((int) (generatedPredator.getY() - generatedPreyAI.getY())) < 10)) {
                    if (generatedPredator.getEnergy() < 260) {
                        generations.deathPrey(generatedPreyAI);
                        generatedPredator.eat();
                    }
                }
            }
        }

        this.foodPoints.removeIf(Food::isEated);

    }

    private void modelViewSet() {
        model.clear();

        for (Predator generatedPredator : predators) {
            model.set((int) generatedPredator.getX(), (int) generatedPredator.getY(), 'P', 40);
        }

        for (Food food : foodPoints) {
            model.set(food.getX(), food.getY(), 'F', 8);
        }

        for (PreyAI generatedPreyAI : AI_prey) {
            model.set((int) generatedPreyAI.getX(), (int) generatedPreyAI.getY(), 'X', 12);
        }

        for (Prey generatedPrey : prey) {
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
                if (!food.isEated()) {
                    food.paint(g);
                }
            }

            for (PreyAI generatedPreyAI : AI_prey) {
                generatedPreyAI.paint(g);
            }

            for (Prey generatedPrey : prey) {
                generatedPrey.paint(g);
            }
        } catch (java.util.ConcurrentModificationException e) {
            //System.out.println("error: java.util.ConcurrentModificationException");
        }

    }

    private void validate() {
        if (prey.size() != 0) {
            for (int i = 0; i < prey.size(); i++) {
                if (!prey.get(i).isAlive()) {
                    prey.remove(i);
                    break;
                }
            }
        }
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
