package Game;

import java.awt.*;

class Organism {
    double x;
    double y;
    double energy;
    boolean isAlive;
    double velocity;
    int directionAngle;
    double radians;

    void move(char[][] model) {
    }

    void feed() {
    }

    void eat() {
    }

    void thinking(char[][] model) {
    }

    void setPosition() {
    }

    void energyCost() {
    }

    void paint(Graphics2D g) {
    }

    void isDead() {
        this.energy = 0.0;
        this.isAlive = false;
    }

    void velocityLimits() {
    }

    void borderBouncing(int x1, int x2) {
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

    double getEnergy() {
        return energy;
    }

    public double getRadians() {
        return radians;
    }
}