package Game;

import GUI.PreyAiGui;
import NeuralNetwork.Genome;

import java.awt.*;

public class PreyAI extends Prey {

    private PreyLogicAI ai;
    private PreyAiGui preyAiGui = new PreyAiGui();
    private String id;
    private double score = 0.0;

    public PreyAI(double xStartPos, double yStartPos) {
        super(xStartPos, yStartPos);
        ai = new PreyLogicAI();
    }

    public PreyAI(double xStartPos, double yStartPos, Genome genome) {
        super(xStartPos, yStartPos);
        ai = new PreyLogicAI(genome);
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
        energy -= 0.01;

        if (x < 180 || x > 1100) {
            energy -= 0.05;
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
}