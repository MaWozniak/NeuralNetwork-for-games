package Main;

import GUI.PreyGui;

import java.awt.*;

class Prey extends Organism {

    int directionAngle;
    private double energy = 100;
    double velocity = 2.0;
    private int leftBorder = 180;
    private int rightBorder = 1018;

    private PreyGui preyGui;

    private SimplyCPUprey simplyCPUprey;


    Prey(double xStartPos, double yStartPos, int updateFramerate) {
        this.x = xStartPos;
        this.y = yStartPos;
        preyGui = new PreyGui();
        simplyCPUprey = new SimplyCPUprey(updateFramerate);
    }

    void move(char[][] model) {
        feed();
        super.move(model);
    }

    void feed() {
        if ((x > 400 && x < 450 && y > 500 && y < 680) || (x > 700 && x < 750 && y > 120 && y < 300)) {
            energy += 0.5;
        }
        if (energy > 130) {
            energy = 130;
        }
    }

    void thinking(char[][] model) {
        simplyCPUprey.thinking(model, this);
    }

    void paint(Graphics2D g) {

        if (isAlive()) {
            preyGui.paint(g, this.x, this.y, this.radians, this.energy);
        }
    }

}