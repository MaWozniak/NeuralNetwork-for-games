package Main;

import GUI.PreyGui;

import java.awt.*;

class Prey extends Organism {

    private SimplyCPUprey simplyCPUprey;
    private PreyGui preyGui;

    Prey(double xStartPos, double yStartPos, int updateFramerate) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.isAlive = true;
        this.energy = 100;
        this.velocity = 2.0;
        preyGui = new PreyGui();
        simplyCPUprey = new SimplyCPUprey(updateFramerate);
    }

    Prey(double xStartPos, double yStartPos) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.isAlive = true;
        this.energy = 100;
        this.velocity = 2.0;
    }

    void move(char[][] model) {
        feed();
        thinking(model);
        setPosition();
        energyCost();
    }

    void feed() {
        if ((x > 400 && x < 450 && y > 500 && y < 680) || (x > 700 && x < 750 && y > 120 && y < 300)) {
            energy += 0.5;
        }
        double maxEnergy = 150;
        if (energy > maxEnergy) {
            energy = maxEnergy;
        }
    }

    void thinking(char[][] model) {
        simplyCPUprey.thinking(model, this);
    }

    void setPosition() {

        velocityLimits();

        this.x += (int) (velocity * Math.cos(radians));
        this.y += (int) (velocity * Math.sin(radians));

        int leftBorder = 22;
        int rightBorder = 1178;
        borderBouncing(leftBorder, rightBorder);
    }

    void energyCost() {
        energy -= 0.01;

        if (x < 180 || x > 1100) {
            energy -= 0.05;
        }

        //DEAD:
        if (energy <= 0) {
            isAlive = false;
        }
    }

    void paint(Graphics2D g) {
        if (isAlive()) {
            preyGui.paint(g, this.x, this.y, this.radians, this.energy);
        }
    }

    void velocityLimits() {
        if (velocity > 8.0) {
            velocity = 6.0;
        }
        if (velocity < -0.6) {
            velocity = 0.5;
        }
    }

    void borderBouncing(int x1, int x2) {
        if ((x < x1)) {
            x += 10;
        }
        if ((y < 22)) {
            y += 10;
        }
        if ((x > x2)) {
            x -= 10;
        }
        if ((y > 778)) {
            y -= 10;
        }
    }
}