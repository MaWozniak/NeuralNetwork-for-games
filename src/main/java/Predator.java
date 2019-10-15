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

    void move(char[][] model) {

        int dirAngle = updateMovement.getDirection();
        double acceleration = updateMovement.getAcceleration();

        directionAngle += dirAngle;
        velocity += acceleration;
        radians = (Math.PI / 180) * (directionAngle);


        //EYES of the Predator
        int k = ((int) x - 5) + (int) (90.0 * Math.cos(radians));
        int l = ((int) y - 5) + (int) (90.0 * Math.sin(radians));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                // directionAngle = 0;
                velocity = 1.2 * velocity;
            }
        }
        k = ((int) x - 5) + (int) (50.0 * Math.cos(radians) - (int) (70.0 * Math.sin(radians)));
        l = ((int) y - 5) + (int) (50.0 * Math.sin(radians) + (int) (70.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                directionAngle = directionAngle + 10;
                velocity = 1.2 * velocity;
            }
        }
        k = ((int) x - 5) + (int) (50.0 * Math.cos(radians) + (int) (70.0 * Math.sin(radians)));
        l = ((int) y - 5) + (int) (50.0 * Math.sin(radians) - (int) (70.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                directionAngle = directionAngle - 10;
                velocity = 1.2 * velocity;
            }
        }

        if (velocity > 12.0) {
            velocity = 2.0;
        }
        //////////////////////////////////


        energy -= 0.05;
        if (energy <= 0) {
            isAlive = false;
        }

        if ((x > 300 && x < 900) && (y > 200 && y < 600)) {
            energy += 0.06;
        }

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


        x += (int) (velocity * Math.cos(radians));
        y += (int) (velocity * Math.sin(radians));

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