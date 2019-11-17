package Game;

import GUI.PreyAiGui;
import NeuralNetwork.Genome;

import java.awt.*;

public class PreyAI extends Prey {

    private PreyLogicAI ai;
    private PreyAiGui preyAiGui = new PreyAiGui();
    private String id;
    private double score = 0.0;
    private boolean energyCost;
    private boolean aging;
    private double age = 0.0;

    public PreyAI(double xStartPos, double yStartPos, boolean energyCost, boolean forcedMove, boolean aging) {
        super(xStartPos, yStartPos);
        ai = new PreyLogicAI(forcedMove);
        this.energyCost = energyCost;
        this.aging = aging;
    }

    public PreyAI(double xStartPos, double yStartPos, Genome genome, boolean energyCost, boolean forcedMove, boolean aging) {
        super(xStartPos, yStartPos);
        ai = new PreyLogicAI(genome, forcedMove);
        this.energyCost = energyCost;
        this.aging = aging;
    }

    void thinking(char[][] model) {
        ai.thinking(model, this);
    }

    void paint(Graphics2D g) {
        if (isAlive()) {
            preyAiGui.paint(g, this.x, this.y, this.angle, this.energy);
        }
    }

    public void setId(int num, int generation) {
        this.id = "gen-" + generation + "[id-" + num + "]";
    }

    public String getId() {
        return id;
    }

    String showGenome() {
        return ai.showGenome();
    }

    public Genome getGenome() {
        return ai.getGenome();
    }

    void energyCost() {
        if (energyCost) {
            energy -= 0.01;
            //energy -= 0.01 * speed/10;

            if (x < 180 || x > 1100) {
                energy -= 0.01;
                //energy -= 0.03 * speed/10;
            }

            //force to move
            if (energy < 100 & speed < 1) {
                energy -= 0.02;
            }
        }
        if (aging) {
            age += 0.01;
        }
    }

    public void isDead() {
        if (this.isAlive) {
            this.energy = 0.0;
            this.isAlive = false;
            System.out.println("death: " + this.getId() + " score: " + this.getScore());
        }
    }

    void updateScore() {
        this.score += 0.001;
    }

    public double getScore() {
        return score;
    }

    public double getAge() {
        return age;
    }
}