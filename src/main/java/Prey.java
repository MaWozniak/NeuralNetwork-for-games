import java.awt.*;

class Prey extends Organism {

    private double velocity = 2.0;

    Prey(double xStartPos, double yStartPos) {
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

        //EYES of the Prey
        int k = (int) x - 5 + (int) (90.0 * Math.cos(radians));
        int l = (int) y - 5 + (int) (90.0 * Math.sin(radians));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                directionAngle = -directionAngle;
                velocity += 2 * velocity;
            }
        }
        k = ((int) x - 5) + (int) (50.0 * Math.cos(radians) - (int) (70.0 * Math.sin(radians)));
        l = ((int) y - 5) + (int) (50.0 * Math.sin(radians) + (int) (70.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                directionAngle = directionAngle - 30;
                velocity = 2 * velocity;
            }
        }
        k = ((int) x - 5) + (int) (50.0 * Math.cos(radians) + (int) (70.0 * Math.sin(radians)));
        l = ((int) y - 5) + (int) (50.0 * Math.sin(radians) - (int) (70.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                directionAngle = directionAngle + 30;
                velocity = 2 * velocity;
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

        this.simplyBouncing();

        x += (int) (velocity * Math.cos(radians));
        y += (int) (velocity * Math.sin(radians));

    }

    void paint(Graphics2D g) {

        if (isAlive) {
            g.setColor(Color.GRAY);
            g.fillOval((int) x - 8, (int) y - 8, 16, 16);

            //temp direction point:
            g.setColor(Color.MAGENTA);
            //x = x * 50 * Math.cos; y = y * 50 * Math.sin
            g.fillOval(((int) x - 5) + (int) (50.0 * Math.cos(radians)), ((int) y - 5) + (int) (50.0 * Math.sin(radians)), 5, 5);
            g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(radians) - (int) (20.0 * Math.sin(radians))), ((int) y - 5) + (int) (40.0 * Math.sin(radians) + (int) (20.0 * Math.cos(radians))), 5, 5);
            g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(radians) + (int) (20.0 * Math.sin(radians))), ((int) y - 5) + (int) (40.0 * Math.sin(radians) - (int) (20.0 * Math.cos(radians))), 5, 5);

            g.setColor(Color.DARK_GRAY);
            if (energy < 60) {
                g.setColor(Color.GRAY);
            }
            if (energy < 30) {
                g.setColor(Color.RED);
            }
            g.drawString(String.valueOf((int) energy), (int) x - 8, (int) y - 8);
        }
    }

}