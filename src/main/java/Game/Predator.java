package Game;

import GUI.PredatorGui;

import java.awt.*;

class Predator extends Organism {
    private PredatorLogicSimple predatorLogicSimple;
    private PredatorGui predatorGui;
    private double maxSpeed = 3;
    private double acc = 0.2;
    private double dec = 0.2;
    private double turnSpeed = 0.15;
    private boolean energyCost;
    private boolean hiddenPlaces;

    Predator(double xStartPos, double yStartPos, int updateFramerate, boolean hiddenPlaces, boolean energyCost) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.isAlive = true;
        this.energy = 130;
        this.speed = 1.0;
        this.hiddenPlaces = hiddenPlaces;
        this.energyCost = energyCost;
        predatorGui = new PredatorGui();
        predatorLogicSimple = new PredatorLogicSimple(updateFramerate);
    }

    void move(char[][] model) {
        thinking(model);
        setPosition();
        energyCost();
    }

    void eat() {
        if (energyCost) {
            energy += 25;
            double maxEnergy = 300;
            if (energy > maxEnergy) {
                energy = maxEnergy;
            }
        }
    }

    void thinking(char[][] model) {
        predatorLogicSimple.thinking(model, this);
    }

    void energyCost() {
        if (energyCost) {
            energy -= 0.04;
            //FULL & TIRED:
            if (energy > 250) {
                down = true;
            }
            //DEAD:
            if (energy <= 0) {
                isAlive = false;
            }
        }
    }

    void velocityLimits() {
        if (speed > maxSpeed) {
            down = true;
        }
        if (speed < -0.01) {
            speed = 0.0;
        }
    }

    void steering() {
        if (up && speed < maxSpeed) {
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

        if (right && speed != 0) {
            angle += turnSpeed * speed / maxSpeed;
        }
        if (left && speed != 0) {
            angle -= turnSpeed * speed / maxSpeed;
        }

        if (right) {
            angle += turnSpeed * 6 / maxSpeed;
        }
        if (left) {
            angle -= turnSpeed * 6 / maxSpeed;
        }
    }

    void setPosition() {
        velocityLimits();
        steering();
        x += Math.sin(angle) * speed;
        y -= Math.cos(angle) * speed;
        int leftBorder = 22;
        int rightBorder = 1178;
//        if (hiddenPlaces) {
//            leftBorder = 190;
//            rightBorder = 1010;
//        }

        borderBouncing(leftBorder, rightBorder);
    }

    void paint(Graphics2D g) {
        if (isAlive) {
            predatorGui.paint(g, this.x, this.y, this.angle, this.energy);
        }
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
        if (hiddenPlaces) {
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
//            if(x > 380 && x < 800 && y > 275 && y < 555) {
//                x = 30;
//                y = 30;
//            }
        }
    }
}