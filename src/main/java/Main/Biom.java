package Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Biom {

    private ModelView model;
    private List<Prey> prey = new ArrayList<>();
    private List<Predator> predators = new ArrayList<>();
    private LifecycleThread lifecycleThread;
    private int organismUpdateFramerate;

    public int getFramerate() {
        return this.lifecycleThread.getFramerate();
    }

    Biom(int numPrey, int numPred, int framerate, boolean fullspeed, ModelView model, int organismUpdateFramerate) {

        this.model = model;
        this.organismUpdateFramerate = organismUpdateFramerate;
        this.addNewPreys(numPrey);
        this.addNewPredators(numPred);
        Thread thread = new Thread(lifecycleThread = new LifecycleThread(framerate, fullspeed, this));
        thread.start();

    }

    void lifecycle() {

        organismsMoves();
        randomAddPrey();
        //randomKillPrey();
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

    void setFramerate(int framerate) {
        this.lifecycleThread.setMillis(1000 / framerate);
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

            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();
            Predator newPredator = new Predator(xStartPos, yStartPos, organismUpdateFramerate);

            predators.add(newPredator);
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
                    generatedPrey.isDead();
                    generatedPredator.eat();
                }
            }
        }

        //model:
        model.clear();

        for (Predator generatedPredator : predators) {
            model.set((int) generatedPredator.getX(), (int) generatedPredator.getY(), 'P');
        }
        for (Prey generatedPrey : prey) {
            model.set((int) generatedPrey.getX(), (int) generatedPrey.getY(), 'X');
        }

        //second frame repaint // comment out to up performance
        model.repaint();
        model.revalidate();

    }

    void paint(Graphics2D g) {

        try {
            for (Predator generatedPredator : predators) {
                generatedPredator.paint(g);
            }

            for (Prey generatedPrey : prey) {
                generatedPrey.paint(g);
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("error: java.util.ConcurrentModificationException");
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
    }
}
