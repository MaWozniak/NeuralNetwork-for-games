package Main;

import GUI.PredatorGui;

import java.awt.*;

class Predator extends Organism {

    int directionAngle;
    private double energy = 100;
    private double maxEnergy = 300;
    double velocity = 2.0;
    private int leftBorder = 22;
    private int rightBorder = 1178;

    private PredatorGui predatorGui;

    private SimplyCPUpredator simplyCPUpredator;


    Predator(double xStartPos, double yStartPos, int updateFramerate) {
        this.x = xStartPos;
        this.y = yStartPos;
        predatorGui = new PredatorGui();
        simplyCPUpredator = new SimplyCPUpredator(updateFramerate);

    }

    void move(char[][] model) {
        super.move(model);
    }

    void energyCost() {

        energy -= 0.03;

        if (x < 180 || x > 1100) {
            energy -= 0.05;
        }

        //FULL & TIRED:
        if (energy > 250) {
            velocity -= 0.35;
            directionAngle = (int) (0.3 * directionAngle);
        }

        if (energy > maxEnergy) {
            energy = maxEnergy;
        }

        //DEAD:
        if (energy <= 0) {
            isAlive = false;
        }
    }

    @Override
    void eat() {
        energy += 25;
    }

    @Override
    void velocityLimits() {
        if (velocity > 10.0) {
            velocity = 2.0;
        }
        if (velocity < -0.6) {
            velocity = 0.5;
        }
    }

    void thinking(char[][] model) {
        simplyCPUpredator.thinking(model, this);
    }

    void paint(Graphics2D g) {

        if (isAlive) {
            predatorGui.paint(g, this.x, this.y, this.radians, this.energy);
        }
    }

}