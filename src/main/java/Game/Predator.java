package Game;

import GUI.PredatorGui;

import java.awt.*;

class Predator extends Organism {

    private PredatorLogicSimple predatorLogicSimple;
    private PredatorGui predatorGui;

    Predator(double xStartPos, double yStartPos, int updateFramerate) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.isAlive = true;
        this.energy = 130;
        this.velocity = 1.0;
        predatorGui = new PredatorGui();
        predatorLogicSimple = new PredatorLogicSimple(updateFramerate);
    }

    void move(char[][] model) {
        thinking(model);
        setPosition();
        energyCost();
    }

    void eat() {
        energy += 25;

        double maxEnergy = 300;
        if (energy > maxEnergy) {
            energy = maxEnergy;
        }

    }

    void thinking(char[][] model) {
        predatorLogicSimple.thinking(model, this);
    }

    void energyCost() {
        energy -= 0.04;

        //FULL & TIRED:
        if (energy > 250) {
            velocity -= 0.35;
            directionAngle = (int) (0.3 * directionAngle);
        }

        //DEAD:
        if (energy <= 0) {
            isAlive = false;
        }
    }

    void velocityLimits() {
        if (velocity > 13.0) {
            velocity = 5.0;
        }
        if (velocity < -0.6) {
            velocity = 0.5;
        }
    }

    void setPosition() {
        velocityLimits();
        this.x += (int) (velocity * Math.cos(radians));
        this.y += (int) (velocity * Math.sin(radians));

        int rightBorder = 1010;
        int leftBorder = 190;
        borderBouncing(leftBorder, rightBorder);
    }

    void paint(Graphics2D g) {
        if (isAlive) {
            predatorGui.paint(g, this.x, this.y, this.radians, this.energy);
        }
    }

    void borderBouncing(int x1, int x2) {
        if ((x < x1)) {
            x += 10;
            velocity = 0.8 * velocity;
        }
        if ((y < 22)) {
            y += 10;
            velocity = 0.8 * velocity;
        }
        if ((x > x2)) {
            x -= 10;
            velocity = 0.8 * velocity;
        }
        if ((y > 778)) {
            y -= 10;
            velocity = 0.8 * velocity;
        }
    }
}