package Game;

import GUI.FoodGui;

import java.awt.Graphics2D;

public class Food {
    int x;
    int y;
    boolean eated = false;
    FoodGui foodGui = new FoodGui();

    Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void paint(Graphics2D g) {
        this.foodGui.paint(g, this.x, this.y);
    }

    void eated() {
        this.eated = true;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isEated() {
        return this.eated;
    }
}
