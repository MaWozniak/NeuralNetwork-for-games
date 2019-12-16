package Game;

import GUI.PreyGui;

import java.awt.*;

class Prey extends Organism {

    private PreyLogicSimple preyLogicSimple;
    private PreyGui preyGui;

    private double maxSpeed = 15;
    private double acc = 0.6;
    private double dec = 0.5;
    private double turnSpeed = 0.2;

    Prey(double xStartPos, double yStartPos, int updateFramerate) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.isAlive = true;
        this.energy = 100;
        this.speed = 0;
        this.angle = 0;
        preyGui = new PreyGui();
        preyLogicSimple = new PreyLogicSimple(updateFramerate);
    }

    Prey(double xStartPos, double yStartPos) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.isAlive = true;
        this.energy = 100;
        this.speed = 0;
        this.angle = 0;
    }

    void move(char[][] model) {
        feed();
        thinking(model);
        setPosition();
        energyCost();
    }

    void feed() {
        if ((x > 400 && x < 450 && y > 500 && y < 680) || (x > 700 && x < 750 && y > 120 && y < 300)) {
            //if (((x > 400 && x < 450 && y > 500 && y < 680) || (x > 700 && x < 750 && y > 120 && y < 300)) && speed>0.1) {
            energy += 0.05;
        }
        double maxEnergy = 150;
        if (energy > maxEnergy) {
            energy = maxEnergy;
        }
    }

    void thinking(char[][] model) {
        preyLogicSimple.thinking(model, this);
    }

    void setPosition() {

        velocityLimits();

        steering();

        x += Math.sin(angle) * speed;
        y -= Math.cos(angle) * speed;

        int leftBorder = 22;
        int rightBorder = 1178;
        borderBouncing(leftBorder, rightBorder);
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

    void energyCost() {
        energy -= 0.01 * speed / 5;

        if (x < 180 || x > 1100) {
            energy -= 0.05 * speed / 5;
        }

        //force to move
        if (energy < 100 & speed < 1) {
            energy -= 0.1;
        }

        //DEAD:
        if (energy <= 0) {
            isAlive = false;
        }
    }

    void paint(Graphics2D g) {
        if (isAlive()) {
            preyGui.paint(g, this.x, this.y, this.angle, this.energy);
        }
    }

    void velocityLimits() {
        if (speed > 8.0) {
            down = true;
        }
        if (speed < -0.1) {
            speed = 0.0;
        }
    }

    void borderBouncing(int x1, int x2) {
        if ((x < x1)) {
            x += 10;
            energy -= 0.01;
        }
        if ((y < 22)) {
            y += 10;
            energy -= 0.05;
        }
        if ((x > x2)) {
            x -= 10;
            energy -= 0.01;
        }
        if ((y > 778)) {
            y -= 10;
            energy -= 0.05;
        }
    }
}