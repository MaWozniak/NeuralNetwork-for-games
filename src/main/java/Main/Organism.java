package Main;

import java.awt.*;

class Organism {
    double x;
    double y;
    boolean isAlive = true;
    private int directionAngle;
    double radians;
    double energy = 100;
    private double velocity;
    private int leftBorder;
    private int rightBorder;
    int dirAngle;
    double acceleration;

    Organism() {
    }

    void move(char[][] model) {
        thinking(model);
        setPosition();
        energyCost();
    }

    void thinking(char[][] model) {
    }

    void paint(Graphics2D g) {
    }

    void eat() {
    }

    void feed() {
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

    void isDead() {
        this.energy = 0.0;
        this.isAlive = false;
    }

    void setPosition() {

        directionAngle += dirAngle;
        velocity += acceleration;
        radians = (Math.PI / 180) * (directionAngle);

        velocityLimits();

        this.x += (int) (velocity * Math.cos(radians));
        this.y += (int) (velocity * Math.sin(radians));

        borderBouncing(this.leftBorder, this.rightBorder);
    }

    void velocityLimits() {
        if (velocity > 8.0) {
            velocity = 2.0;
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

    boolean isAlive() {
        return isAlive;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}