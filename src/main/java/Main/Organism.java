package Main;

import java.awt.*;

class Organism {
    double x;
    double y;
    boolean isAlive = true;
    double radians;
    int directionAngle = 0;
    private double energy;
    UpdateMovement updateMovement;

    Organism(int updateFramerate) {
        updateMovement = new UpdateMovement(updateFramerate);
        updateMovement.start();
    }

    void simplyBouncing(int x1, int x2) {
        //simplest bouncing on borders:
        if ((x < x1)) {
            //directionAngle = -directionAngle;
            x += 10;
        }
        if ((y < 22)) {
            //directionAngle = -directionAngle;
            y += 10;
        }
        if ((x > x2)) {
            //directionAngle = -directionAngle;
            x -= 10;
        }
        if ((y > 778)) {
            //directionAngle = -directionAngle;
            y -= 10;
        }
    }

    void paint(Graphics2D g) {

    }

    void eat() {
        energy += 5;
    }

    void isDead() {
        this.energy = 0.0;
        this.isAlive = false;
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