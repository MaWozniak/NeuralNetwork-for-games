package Main;

import java.awt.*;

class Predator extends Organism {

    private double energy = 200;
    private double maxEnergy = 300;
    private double velocity = 4.0;

    Predator(double xStartPos, double yStartPos, int updateFramerate) {
        super(updateFramerate);
        this.x = xStartPos;
        this.y = yStartPos;
    }

    void move(char[][] model) {

        int dirAngle = updateMovement.getDirection();
        double acceleration = updateMovement.getAcceleration();

        directionAngle += dirAngle;
        velocity += acceleration;
        radians = (Math.PI / 180) * (directionAngle);


        //EYES of the main.Predator
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
        //////////////////////////////////


        energy -= 0.03;
        if (energy <= 0) {
            isAlive = false;
        }

//        if ((x > 300 && x < 900) && (y > 200 && y < 600)) {
//            energy += 0.06;
//        }

        if (energy > 250) {
            velocity -= 0.35;
            directionAngle = (int) (0.3 * directionAngle);
        }

        if (energy > maxEnergy) {
            energy = maxEnergy;
        }

        if (velocity > 10.0) {
            velocity = 2.0;
        }
        if (velocity < -0.6) {
            velocity = 0.5;
        }

        x += (int) (velocity * Math.cos(radians));
        y += (int) (velocity * Math.sin(radians));

        this.simplyBouncing(180, 1010);

    }

    @Override
    void eat() {
        energy += 25;
    }

    void paint(Graphics2D g) {

        if (isAlive) {
            g.setColor(Color.RED);
            //g.setColor(Color.YELLOW);
            g.fillOval((int) x - 15, (int) y - 15, 20, 20);

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
    }

}