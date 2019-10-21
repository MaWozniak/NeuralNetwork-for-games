package Main;

import NeuralNetwork.BlackBoxAI;

import java.awt.*;

class PreyAI2 {
    double x;
    double y;
    boolean isAlive = true;
    double radians;
    int directionAngle = 0;
    private double energy = 100;
    private double velocity = 2.0;
    private BlackBoxAI ai = new BlackBoxAI();

    PreyAI2(double xStartPos, double yStartPos) {
        this.x = xStartPos;
        this.y = yStartPos;

    }

    void move(char[][] model) {

        //
        OutputMove move = ai.move(model);
        int dirAngle = (int) move.getAngle();
        double acceleration = move.getAccelleration();
        //

        ////////////////////////////////////////
        // compute direction and velocity from CPU inputs
        directionAngle += dirAngle;
        velocity += acceleration;
        radians = (Math.PI / 180) * (directionAngle);
        ///////////////////////////////////////

        ///////////////////////////////////
        // VIEW AND LOGIC OF THE SIMPLE CPU AI:
        /*
        int k = (int) x - 5 + (int) (90.0 * Math.cos(radians));
        int l = (int) y - 5 + (int) (90.0 * Math.sin(radians));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                directionAngle = -directionAngle;
                velocity += 2 * velocity;
            }
        }
        k = ((int) x - 5) + (int) (40.0 * Math.cos(radians) - (int) (40.0 * Math.sin(radians)));
        l = ((int) y - 5) + (int) (40.0 * Math.sin(radians) + (int) (40.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                directionAngle = directionAngle - 30;
                velocity = 2 * velocity;
            }
        }
        k = ((int) x - 5) + (int) (40.0 * Math.cos(radians) + (int) (40.0 * Math.sin(radians)));
        l = ((int) y - 5) + (int) (40.0 * Math.sin(radians) - (int) (40.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                directionAngle = directionAngle + 30;
                velocity = 2 * velocity;
            }
        }
         */

        //////////////////////////////////////////
        // velocity limit
        if (velocity > 8.0) {
            velocity = 2.0;
        }
        if (velocity < -0.6) {
            velocity = 0.5;
        }
        //////////////////////////////////
        // feeding and live managmet
        if (x < 180 || x > 1100) {
            energy -= 0.05;
        }
        energy -= 0.01;
        if ((x > 400 && x < 450 && y > 500 && y < 680) || (x > 700 && x < 750 && y > 120 && y < 300)) {
            energy += 0.5;
        }
        if (energy > 130) {
            energy = 130;
        }
        if (energy <= 0) {
            isAlive = false;
        }

        if ((x > 300 && x < 900) && (y > 200 && y < 600)) {
            energy += 0.06;
        }

        /////////////////////////////////////////////
        // final result of arguments
        x += (int) (velocity * Math.cos(radians));
        y += (int) (velocity * Math.sin(radians));

        // limits of the board
        this.simplyBouncing(22, 1178);

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
        if (isAlive) {
            g.setColor(Color.BLUE);
            //g.setColor(Color.GREEN);
            g.fillOval((int) x - 8, (int) y - 8, 12, 12);

            //temp direction point:
            g.setColor(Color.BLACK);
            //x = x * 50 * Math.cos; y = y * 50 * Math.sin
            g.fillOval(((int) x - 5) + (int) (50.0 * Math.cos(radians)), ((int) y - 5) + (int) (50.0 * Math.sin(radians)), 5, 5);
            g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(radians) - (int) (40.0 * Math.sin(radians))), ((int) y - 5) + (int) (40.0 * Math.sin(radians) + (int) (40.0 * Math.cos(radians))), 5, 5);
            g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(radians) + (int) (40.0 * Math.sin(radians))), ((int) y - 5) + (int) (40.0 * Math.sin(radians) - (int) (40.0 * Math.cos(radians))), 5, 5);

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