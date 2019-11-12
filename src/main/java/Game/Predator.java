package Game;

import GUI.PredatorGui;

import java.awt.*;

class Predator extends Organism {

    private PredatorLogicSimple predatorLogicSimple;
    private PredatorGui predatorGui;

    private double maxSpeed = 15;
    private double acc = 0.3;
    private double dec = 0.5;
    private double turnSpeed = 0.2;

    Predator(double xStartPos, double yStartPos, int updateFramerate) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.isAlive = true;
        this.energy = 130;
        this.speed = 1.0;
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
            speed -= 0.35;
            angle = (int) (0.3 * angle);
        }

        //DEAD:
        if (energy <= 0) {
            isAlive = false;
        }
    }

    void velocityLimits() {
        if (speed > 13.0) {
            down = true;
        }
        if (speed < -0.1) {
            speed = 0.0;
        }
    }

    void steering() {
        if (up && speed < maxSpeed) {
            if (speed < 0) {
                speed += dec;
            } else {
                speed += acc;
            }
        }

        if (down && speed > 0) {
            speed -= acc;
        }
        if (!up && !down) {
            if ((speed - dec) > 0) {
                speed -= dec;
            } else if ((speed + dec) < 0) {
                speed += dec;
            } else {
                speed = 0;
            }
        }

        if (right && speed != 0) {
            angle += turnSpeed * speed / maxSpeed;
        }
        if (left && speed != 0) {
            angle -= turnSpeed * speed / maxSpeed;
        }

        if (right) {
            angle += turnSpeed * 6 / maxSpeed;
        }
        if (left) {
            angle -= turnSpeed * 6 / maxSpeed;
        }
    }

    void setPosition() {

        velocityLimits();

        steering();

        x += Math.sin(angle) * speed;
        y -= Math.cos(angle) * speed;

        int rightBorder = 1010;
        int leftBorder = 190;
        borderBouncing(leftBorder, rightBorder);
    }

    void paint(Graphics2D g) {
        if (isAlive) {
            predatorGui.paint(g, this.x, this.y, this.angle, this.energy);
        }
    }

    void borderBouncing(int x1, int x2) {
        if ((x < x1)) {
            x += 10;
            //speed = 0.8 * speed;
        }
        if ((y < 22)) {
            y += 10;
            //speed = 0.8 * speed;
        }
        if ((x > x2)) {
            x -= 10;
            //speed = 0.8 * speed;
        }
        if ((y > 778)) {
            y -= 10;
            //speed = 0.8 * speed;
        }
    }
}