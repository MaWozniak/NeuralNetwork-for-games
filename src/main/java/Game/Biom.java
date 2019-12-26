package Game;

import Genetics.Generations;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Biom {

    private Generations generations;

    private ModelView model;
    private int numPrey;
    private List<Prey> prey = new ArrayList<>();
    private List<PreyAI> AI_prey = new ArrayList<>();
    private List<Predator> predators = new ArrayList<>();
    private int predatorsNumber;
    private LifecycleThread lifecycleThread;
    private int organismUpdateFramerate;
    private boolean hiddenPlaces;
    private boolean preyAiEnergyCost;
    private boolean preyForcedMove;
    private boolean predatorsEnergyCost;
    private boolean preyAging;
    private double preyMaxAge;

    Biom(int numPrey, int numPred, int numAIPrey, int framerate, boolean fullspeed, ModelView model, int organismUpdateFramerate,
         boolean hiddenPlaces, boolean predatorsEnergyCost, boolean preyAiEnergyCost, boolean preyForcedMove, boolean preyAging, double preyMaxAge) {

        this.model = model;
        this.organismUpdateFramerate = organismUpdateFramerate;
        this.preyAiEnergyCost = preyAiEnergyCost;
        this.preyAging = preyAging;
        this.preyMaxAge = preyMaxAge;
        this.preyForcedMove = preyForcedMove;
        this.numPrey = numPrey;
        this.addNewPreys(numPrey);
        this.generations = new Generations(AI_prey, numAIPrey, preyAiEnergyCost, preyForcedMove, preyAging);
        this.generations.addFirstGeneration();
        this.hiddenPlaces = hiddenPlaces;
        this.predatorsEnergyCost = predatorsEnergyCost;
        this.predatorsNumber = numPred;
        this.addNewPredators(numPred);
        Thread thread = new Thread(lifecycleThread = new LifecycleThread(framerate, fullspeed, this));
        thread.start();

    }

    public int getFramerate() {
        return this.lifecycleThread.getFramerate();
    }

    public void setFramerate(int framerate) {
        this.lifecycleThread.setMillis(1000 / framerate);
    }

    void lifecycle() throws IOException {

        organismsMoves();
        modelViewSet();
        //randomAddPrey();
        scoreAi();
        checkPredators(predatorsNumber);
        checkGenerations();
        validate();

    }

    private void randomAddPrey() {
        double randomNum = Math.random();
        if (randomNum > 0.995) {
            this.addNewPreys(1);
        }
    }

    void randomKillPrey() {
        double randomNum = Math.random();
        if (!(prey.size() == 0)) {
            if (randomNum > 0.99) {
                if (prey.get(0).isAlive())
                    this.prey.remove(0);
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

            double xStartPos = 800 * Math.random() + 150;
            double yStartPos = 750 * Math.random();
            Predator newPredator = new Predator(xStartPos, yStartPos, organismUpdateFramerate, this.hiddenPlaces, this.predatorsEnergyCost);

            predators.add(newPredator);
        }
    }

    private void checkPredators(int number) {
        if (predators.size() < number) this.addNewPredators(1);
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
                if ((Math.abs((int) (generatedPredator.getX() - generatedPrey.getX())) < 20) && (Math.abs((int) (generatedPredator.getY() - generatedPrey.getY())) < 20)) {
                    if (generatedPredator.getEnergy() < 250) {
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

            //simple kill - right now main.Predator see all board and all main.Prey, doesn't has a FIELD OF VIEW
            for (Predator generatedPredator : predators) {
                if ((Math.abs((int) (generatedPredator.getX() - generatedPreyAI.getX())) < 20) && (Math.abs((int) (generatedPredator.getY() - generatedPreyAI.getY())) < 20)) {
                    if (generatedPredator.getEnergy() < 260) {
                        generations.deathPrey(generatedPreyAI);
                        generatedPredator.eat();
                    }
                }
            }
        }

    }

//    private void checkAiPreiToShowNetwork() {
//        List<PreyAI> AI_prey_alive = new ArrayList<>();
//        for (PreyAI generatedPreyAI : AI_prey) {
//            if(generatedPreyAI.isAlive()){
//                AI_prey_alive.add(generatedPreyAI);
//            }
//        }
//        AI_prey_alive.get(0).setFirstInGeneration(true);
//        AI_prey_alive.clear();
//    }

    private void modelViewSet() {
        model.clear();

        for (Predator generatedPredator : predators) {
            model.set((int) generatedPredator.getX(), (int) generatedPredator.getY(), 'P', 40);
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
        if (!(prey.size() == 0)) {
            for (int i = 0; i < prey.size(); i++) {
                if (!prey.get(i).isAlive()) {
                    prey.remove(i);
                    break;
                }
            }
        }
        if (!(predators.size() == 0)) {
            for (int i = 0; i < predators.size(); i++) {
                if (!predators.get(i).isAlive()) {
                    predators.remove(i);
                    break;
                }
            }
        }
        if (!(AI_prey.size() == 0)) {
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


}
