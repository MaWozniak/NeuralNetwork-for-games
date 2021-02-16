package game.food;

import gui.FoodGui;

import java.awt.Graphics2D;

public class Food {
    int x;
    int y;
    boolean eaten = false;
    FoodGui foodGui = new FoodGui();

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics2D g) {
        this.foodGui.paint(g, this.x, this.y);
    }

    public void eated() {
        this.eaten = true;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isEaten() {
        return this.eaten;
    }
}
