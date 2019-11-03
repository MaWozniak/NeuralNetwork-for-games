package Game;

import GUI.PreyAiGui;

import java.awt.*;

class PreyAI extends Prey {

    private PreyLogicAI ai = new PreyLogicAI();
    private PreyAiGui preyAiGui = new PreyAiGui();
    private String id;
    private double score = 0.0;

    PreyAI(double xStartPos, double yStartPos) {
        super(xStartPos, yStartPos);
    }

    void thinking(char[][] model) {
        ai.thinking(model, this);
    }

    void paint(Graphics2D g) {
        if (isAlive()) {
            preyAiGui.paint(g, this.x, this.y, this.radians, this.energy);
        }
    }

    void setId(int num, int generation) {
        this.id = "gen-" + generation + "[id-" + num + "]";
    }

    private String getId() {
        return id;
    }

    String showGenome() {
        return ai.showGenome();
    }

    void isDead() {
        this.energy = 0.0;
        this.isAlive = false;
        System.out.println("death: " + this.getId() + " score: " + this.getScore());
    }

    void updateScore() {
        this.score += 0.001;
    }

    private double getScore() {
        return score;
    }
}