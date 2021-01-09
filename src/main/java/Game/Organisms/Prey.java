package Game.Organisms;

import GUI.NeuralNetworkView;
import GUI.PreyAiGui;
import NeuralNetwork.Genome;
import lombok.Data;

import java.awt.*;

@Data
public class Prey {
    private static final boolean ENERGY_COST = true;
    private static final boolean AGING = true;
    private static final boolean FORCE_TO_MOVE = false;
    private boolean firstInGeneration;
    double x;
    double y;
    double energy;
    boolean isAlive;
    double speed;
    double angle;
    double preyMaxAge;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    protected double maxSpeed = 15;
    private PreyLogic ai;
    private PreyAiGui preyAiGui = new PreyAiGui();
    private NeuralNetworkView networkView;
    private String id;
    private double stamina = 10;
    private double acc = 0.8;
    private double dec = 0.8;
    private double turnSpeed = 0.5;
    private double score = 0.0;
    private double age = 0.0;

    public Prey(double xStartPos, double yStartPos, Genome genome, boolean firstInGeneration, double preyMaxAge) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.preyMaxAge = preyMaxAge;
        this.firstInGeneration = firstInGeneration;
        this.isAlive = true;
        this.energy = 30;
        this.speed = 0;
        this.angle = 0;
        ai = new PreyLogic(genome, FORCE_TO_MOVE);
        if (firstInGeneration) {
            this.networkView = new NeuralNetworkView();
        }
    }

    public void move(char[][] model) {
        thinking(model);
        setPosition();
        energyCost();
    }

    public double getX() {
        return this.x;
    }

    public double getPreyMaxAge() {
        return preyMaxAge;
    }

    public void setFirstInGeneration(boolean firstInGeneration) {
        this.firstInGeneration = firstInGeneration;
    }

    void thinking(char[][] model) {
        ai.thinking(model, this);
    }

    public void paint(Graphics2D g) {
        if (isAlive()) {
            preyAiGui.paint(g, this.x, this.y, this.angle, this.energy, this.firstInGeneration);
        }
        if (firstInGeneration) {
            networkView.paint(g, x, y, angle, energy, ai.getInputs(), ai.getOutputs());
        }
    }

    public void setId(int num, int generation) {
        this.id = "gen-" + generation + "[id-" + num + "]";
    }

    public String getId() {
        return id;
    }

    public Genome getGenome() {
        return ai.getGenome();
    }

    void setPosition() {
        velocityLimits();
        steering();
        x += Math.sin(angle) * speed;
        y -= Math.cos(angle) * speed;
        int leftBorder = 22;
        int rightBorder = 1178;
        borderBouncing(leftBorder, rightBorder);

    }

    void borderBouncing(int x1, int x2) {
        double penalty = 0.02;
        if ((x < x1)) {
            x += 10;
            energy -= penalty;
        }
        if ((y < 22)) {
            y += 10;
            energy -= penalty;
        }
        if ((x > x2)) {
            x -= 10;
            energy -= penalty;
        }
        if ((y > 778)) {
            y -= 10;
            energy -= penalty;
        }
    }

    void velocityLimits() {
        if (speed > maxSpeed) {
            down = true;
        }
        if (speed < -0.1) {
            speed = 0.0;
        }
    }

    void steering() {
        maxSpeed = 3 * stamina;
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
            if (speed - dec > 0) {
                speed -= dec;
            } else if (speed + dec < 0) {
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


    void energyCost() {
        if (ENERGY_COST) {
            energy -= 0.1;
            energy -= Math.abs(0.2 * speed / maxSpeed);
        }

        if (AGING) {
            age += 0.01;
        }

        if (stamina > 0) {
            stamina -= 0.01 * speed;
        }

        if (speed < 2.5 && stamina < 10) {
            stamina += 0.02;
        }

    }

    public void feed() {
        energy += 80;
        double maxEnergy = 150;
        if (energy > maxEnergy) {
            energy = maxEnergy;
        }

    }

    public void isDead() {
        if (this.isAlive) {
            this.energy = 0.0;
            this.isAlive = false;
            System.out.println("death: " + this.getId() + " score: " + this.getScore());
        }
    }

    public void updateScore() {
        this.score += 0.001;
    }

    public double getScore() {
        return score;
    }

    public double getAge() {
        return age;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public double getStamina() {
        return this.stamina;
    }

    public double getAngle() {
        return this.angle;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public double getEnergy() {
        return energy;
    }
}