package game.organisms;

import gui.PredatorGui;
import game.stage.Stage;

import java.awt.*;

public class SimplePredator implements Predator {
    private static final boolean ENERGY_COST = true;
    private static final double MAX_SPEED = 5;

    double x;
    double y;
    double energy;
    boolean isAlive;
    double speed;
    double angle;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    private final SimplePredatorLogic predatorLogic;
    private final PredatorGui predatorGui;
    private boolean testingIfSee = false;

    public SimplePredator(double xStartPos, double yStartPos) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.isAlive = true;
        this.energy = 130;
        this.speed = 1.0;
        predatorGui = new PredatorGui();
        predatorLogic = new SimplePredatorLogic();
    }

    public void move(char[][] model, Stage stage) {
        thinking(model);
        setPosition(stage);
        energyCost();
    }

    public void eat() {
        if (ENERGY_COST) {
            energy += 25;
            double maxEnergy = 300;
            if (energy > maxEnergy) {
                energy = maxEnergy;
            }
        }
    }

    void thinking(char[][] model) {
        predatorLogic.thinking(model, this);
    }

    void energyCost() {
        if (ENERGY_COST) {
            energy -= 0.04;
            //FULL & TIRED:
            if (energy > 250) {
                down = true;
            }
            //DEAD:
            if (energy <= 0) {
                isAlive = false;
                predatorLogic.killThread();
            }
        }
    }

    void steering() {
        double acc = 0.3;
        double dec = 0.1;
        if (up && speed < MAX_SPEED) {
            if (speed < 0) {
                speed += dec;
            } else {
                speed += acc;
            }
        }

        if (down && speed > 0) {
            speed -= acc;
        }
        if (!up && !down) {
            if ((speed - dec) > 0) {
                speed -= dec;
            } else if ((speed + dec) < 0) {
                speed += dec;
            } else {
                speed = 0;
            }
        }

        double turnSpeed = 0.15;
        if (right && speed != 0) {
            angle += turnSpeed * speed / MAX_SPEED;
        }
        if (left && speed != 0) {
            angle -= turnSpeed * speed / MAX_SPEED;
        }

        if (right) {
            angle += turnSpeed * 6 / MAX_SPEED;
        }
        if (left) {
            angle -= turnSpeed * 6 / MAX_SPEED;
        }
    }

    void setPosition(Stage stage) {
        velocityLimits();
        steering();
        x += Math.sin(angle) * speed;
        y -= Math.cos(angle) * speed;
        int leftBorder = 22;
        int rightBorder = 1178;

        borderBouncing(leftBorder, rightBorder, stage);
    }

    public void paint(Graphics2D g) {
        if (isAlive) {
            Color color = Color.RED;
            predatorGui.paint(g, this.x, this.y, this.angle, this.energy, color);
        }
    }


    void borderBouncing(int x1, int x2, Stage stage) {
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
        if (stage.isHidePlacesBorder()) {
            if ((x < 84)) {
                x += 10;
            }
            if ((y < 84)) {
                y += 10;
            }
            if ((x > 1036)) {
                x -= 10;
            }
            if ((y > 647)) {
                y -= 10;
            }
        }
        if (stage.isHidePlacesCenter()) {
            if (x > 380 && x < 430 && y > 275 && y < 555) {
                x += 20;
            }
            if (x > 750 && x < 810 && y > 275 && y < 555) {
                x -= 20;
            }
            if (x > 380 && x < 800 && y > 275 && y < 325) {
                y -= 20;
            }
            if (x > 380 && x < 800 && y > 515 && y < 565) {
                y += 20;
            }
            if (x > 430 && x < 750 && y > 325 && y < 515) {
                x = 30;
                y = 30;
            }
        }
    }

    void velocityLimits() {
        if (speed > MAX_SPEED) {
            down = true;
        }
        if (speed < -0.01) {
            speed = 0.0;
        }
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return y;
    }

    public double getEnergy() {
        return energy;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public boolean isTestingIfSee() {
        return testingIfSee;
    }

    public void setTestingIfSee(boolean testingIfSee) {
        this.testingIfSee = testingIfSee;
    }

    public double getMaxSpeed() {
        return MAX_SPEED;
    }
}