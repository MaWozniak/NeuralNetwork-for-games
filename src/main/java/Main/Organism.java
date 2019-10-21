package Main;

import java.awt.*;

class Organism {
    double x;
    double y;
    boolean isAlive = true;
    double radians;
    int directionAngle = 0;
    double energy;
    UpdateMovement updateMovement;

    Organism(int updateFramerate) {
        updateMovement = new UpdateMovement(updateFramerate);
        updateMovement.start();
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

    void paint(Graphics2D g) {
    }

    void eat() {
    }

    void feed() {
    }

    void isDead() {
        this.energy = 0.0;
        this.isAlive = false;
    }

    /*void setPosition(int dirAngle, double acceleration){

        directionAngle += dirAngle;
        velocity += acceleration;
        radians = (Math.PI / 180) * (directionAngle);
    }

    void velocityLimits(){
        if (velocity > 8.0) {
            velocity = 2.0;
        }
        if (velocity < -0.6) {
            velocity = 0.5;
        }
    }
*/


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