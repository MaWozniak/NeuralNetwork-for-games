import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class OrganismsGenerator {

    private List<Prey> generatedPreys = new ArrayList<>();

    List<Prey> getGeneratedPreys() {
        return generatedPreys;
    }

    private Predator predator = new Predator(100, 400, 1);

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

    OrganismsGenerator(int number) {

        this.addNewPreys(number);

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

    void move() {

        predator.move();

        for (Prey generatedPrey : generatedPreys) {
            generatedPrey.move();

            //simple kill - right now Predator see all board and all Prey, doesn't has a FIELD OF VIEW
            if ((Math.abs((int) (predator.getX() - generatedPrey.getX())) < 20) && (Math.abs((int) (predator.getY() - generatedPrey.getY())) < 20)) {
                generatedPrey.isDead();
            }
        }

    }

    void paint(Graphics2D g) {

        for (Prey generatedPrey : generatedPreys) {
            generatedPrey.paint(g);
        }

        predator.paint(g);
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
    }
}
