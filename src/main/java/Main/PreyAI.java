package Main;

import GUI.PreyAiGui;

import java.awt.*;

class PreyAI extends Prey {

    private AiCPUprey ai = new AiCPUprey();
    private PreyAiGui preyAiGui = new PreyAiGui();

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
}