package Main;

import GUI.PreyGui;

import java.awt.*;

class Prey2 extends Organism2 {

    public int directionAngle;
    private double energy = 100;
    private double velocity = 2.0;
    private int leftBorder = 22;
    private int rightBorder = 1178;

    private PreyGui preyGui;

    private SimplyCPUprey simplyCPUprey;


    Prey2(double xStartPos, double yStartPos, int updateFramerate) {
        this.x = xStartPos;
        this.y = yStartPos;
        preyGui = new PreyGui();

    }

    void move(char[][] model) {
        feed();
        super.move(model);
    }

    void thinking(char[][] model) {
        simplyCPUprey.thinking(model, this);
    }

    void paint(Graphics2D g) {

        if (isAlive) {
            preyGui.paint(g);
        }
    }

}