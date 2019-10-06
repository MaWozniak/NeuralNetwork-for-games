import java.awt.*;

class Predator {
    private double x;
    private double y;
    private double energy = 100;
    private boolean isAlive = true;

    private UpdateMovement updateMovement = new UpdateMovement();

    private double radians;
    private int directionAngle = 0;
    private double velocity = 4.0;

    Predator(double xStartPos, double yStartPos, int id) {
        this.x = xStartPos;
        this.y = yStartPos;
        updateMovement.start();
    }

    void move() {

        int dirAngle = updateMovement.getDirection();
        double acceleration = updateMovement.getAcceleration();

        directionAngle += dirAngle;
        velocity += acceleration;
        radians = (Math.PI / 180) * (directionAngle);

        energy -= 0.05;
        if (energy <= 0) {
            isAlive = false;
        }

        if ((x > 300 && x < 900) && (y > 200 && y < 600)) {
            energy += 0.06;
        }

        x += (int) (velocity * Math.cos(radians));
        y += (int) (velocity * Math.sin(radians));

        //simplest bouncing on borders:
        if ((x < 5)) {
            directionAngle = -directionAngle;
            x += 3;
        }
        if ((y < 5)) {
            directionAngle = -directionAngle;
            y += 3;
        }
        if ((x > 1195)) {
            directionAngle = -directionAngle;
            x -= 3;
        }
        if ((y > 785)) {
            directionAngle = -directionAngle;
            y -= 3;
        }
    }

    void paint(Graphics2D g) {

        if (isAlive) {
            g.setColor(Color.RED);
            g.fillOval((int) x - 15, (int) y - 15, 30, 30);

            //temp direction point:
            g.setColor(Color.BLUE);
            g.fillOval(((int) x - 5) + (int) (90.0 * Math.cos(radians)), ((int) y - 5) + (int) (90.0 * Math.sin(radians)), 5, 5);
            g.fillOval(((int) x - 5) + (int) (80.0 * Math.cos(radians) - (int) (40.0 * Math.sin(radians))), ((int) y - 5) + (int) (80.0 * Math.sin(radians) + (int) (40.0 * Math.cos(radians))), 5, 5);
            g.fillOval(((int) x - 5) + (int) (80.0 * Math.cos(radians) + (int) (40.0 * Math.sin(radians))), ((int) y - 5) + (int) (80.0 * Math.sin(radians) - (int) (40.0 * Math.cos(radians))), 5, 5);
            g.fillOval(((int) x - 5) + (int) (50.0 * Math.cos(radians) - (int) (70.0 * Math.sin(radians))), ((int) y - 5) + (int) (50.0 * Math.sin(radians) + (int) (70.0 * Math.cos(radians))), 5, 5);
            g.fillOval(((int) x - 5) + (int) (50.0 * Math.cos(radians) + (int) (70.0 * Math.sin(radians))), ((int) y - 5) + (int) (50.0 * Math.sin(radians) - (int) (70.0 * Math.cos(radians))), 5, 5);

            g.setColor(Color.DARK_GRAY);
            if (energy < 60) {
                g.setColor(Color.GRAY);
            }
            if (energy < 30) {
                g.setColor(Color.RED);
            }
            g.drawString(String.valueOf((int) energy), (int) x - 8, (int) y - 8);
        }

        int[] xpoints = {(int) x, (int) (x + 10 * velocity * Math.cos(radians) - 25), (int) (x + 10 * velocity * Math.cos(radians)) + 25};
        int[] ypoints = {(int) y, (int) (y + 10 * velocity * Math.sin(radians)), (int) (y + 10 * velocity * Math.sin(radians))};
        int npoints = 3;

        //g.fillPolygon(xpoints, ypoints, npoints);
    }

    public boolean isAlive() {
        return isAlive;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}