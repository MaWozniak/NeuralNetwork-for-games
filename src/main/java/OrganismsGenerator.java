import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class OrganismsGenerator {

    List<Prey> getGeneratedPreys() {
        return generatedPreys;
    }

    public ModelView model = new ModelView();

    private List<Prey> generatedPreys = new ArrayList<>();
    private List<Predator> generatedPredators = new ArrayList<>();

    void randomAddPrey() {
        double randomNum = Math.random();
        if (randomNum > 0.98) {
            this.addNewPreys(1);
        }
    }

    void randomKillPrey() {
        double randomNum = Math.random();
        if (!(generatedPreys.size() == 0)) {
            if (randomNum > 0.99) {
                if (generatedPreys.get(0).isAlive())
                    this.generatedPreys.remove(0);
            }
        }

    }

    OrganismsGenerator(int numPrey, int numPred) throws InterruptedException {

        this.addNewPreys(numPrey);
        this.addNewPredators(numPred);

    }

    private void addNewPreys(int number) {

        for (int i = 0; i < number; i++) {

            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();
            int id = (int) (1000 * Math.random());
            Prey newPrey = new Prey(xStartPos, yStartPos, id);

            generatedPreys.add(newPrey);
        }
    }

    private void addNewPredators(int number) {

        for (int i = 0; i < number; i++) {

            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();
            int id = (int) (1000 * Math.random());
            Predator newPredator = new Predator(xStartPos, yStartPos, id);

            generatedPredators.add(newPredator);
        }
    }

    void move() {

        for (Predator generatedPredator : generatedPredators) {
            generatedPredator.move(model.getModel());
        }

        for (Prey generatedPrey : generatedPreys) {
            generatedPrey.move(model.getModel());

            //simple kill - right now Predator see all board and all Prey, doesn't has a FIELD OF VIEW
            for (Predator generatedPredator : generatedPredators) {
                if ((Math.abs((int) (generatedPredator.getX() - generatedPrey.getX())) < 20) && (Math.abs((int) (generatedPredator.getY() - generatedPrey.getY())) < 20)) {
                    generatedPrey.isDead();
                }
            }
        }

        //model:
        model.clear();

        for (Predator generatedPredator : generatedPredators) {
            model.set((int) generatedPredator.getX(), (int) generatedPredator.getY(), 'P');
        }
        for (Prey generatedPrey : generatedPreys) {
            model.set((int) generatedPrey.getX(), (int) generatedPrey.getY(), 'X');
        }

        //second frame repaint // comment out to up performance
        model.repaint();
        model.revalidate();

    }

    void paint(Graphics2D g) {

        for (Predator generatedPredator : generatedPredators) {
            generatedPredator.paint(g);
        }

        for (Prey generatedPrey : generatedPreys) {
            generatedPrey.paint(g);
        }
    }

    void validate() {
        if (!(generatedPreys.size() == 0)) {
            for (int i = 0; i < generatedPreys.size(); i++) {
                if (!generatedPreys.get(i).isAlive()) {
                    generatedPreys.remove(i);
                    break;
                }
            }
        }
        if (!(generatedPredators.size() == 0)) {
            for (int i = 0; i < generatedPredators.size(); i++) {
                if (!generatedPredators.get(i).isAlive()) {
                    generatedPredators.remove(i);
                    break;
                }
            }
        }
    }
}