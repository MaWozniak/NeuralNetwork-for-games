import java.awt.*;

class Organism {
    double x;
    double y;
    double energy = 100;
    boolean isAlive = true;
    double radians;
    int directionAngle = 0;
    private double velocity;

    UpdateMovement updateMovement = new UpdateMovement();

    Organism() {
    }

    void simplyBouncing() {
        //simplest bouncing on borders:
        if ((x < 22)) {
            //directionAngle = -directionAngle;
            x += 25;
            //  velocity = 0.0;
        }
        if ((y < 22)) {
            //directionAngle = -directionAngle;
            y += 25;
            // velocity = 0.0;
        }
        if ((x > 1178)) {
            //directionAngle = -directionAngle;
            x -= 25;
            //  velocity = 0.0;
        }
        if ((y > 778)) {
            //directionAngle = -directionAngle;
            y -= 25;
            // velocity = 0.0;
        }
    }

    void paint(Graphics2D g) {

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