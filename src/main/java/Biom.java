import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Biom {

    private ModelView model;
    private List<Prey> prey = new ArrayList<>();
    private List<Predator> predators = new ArrayList<>();


    Biom(int numPrey, int numPred, int framerate, ModelView model) {

        this.addNewPreys(numPrey);
        this.addNewPredators(numPred);
        this.model = model;
        Thread thread = new Thread(new LifecycleThread(framerate, this));
        thread.start();


    }

    void lifecycle() {

        organismsMoves();
        randomAddPrey();
        //randomKillPrey();
        validate();
    }

    void randomAddPrey() {
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
            Prey newPrey = new Prey(xStartPos, yStartPos);

            prey.add(newPrey);
        }
    }

    private void addNewPredators(int number) {

        for (int i = 0; i < number; i++) {

            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();
            Predator newPredator = new Predator(xStartPos, yStartPos);

            predators.add(newPredator);
        }
    }

    private void organismsMoves() {

        for (Predator generatedPredator : predators) {
            generatedPredator.move(model.getModel());
        }

        for (Prey generatedPrey : prey) {
            generatedPrey.move(model.getModel());

            //simple kill - right now Predator see all board and all Prey, doesn't has a FIELD OF VIEW
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
