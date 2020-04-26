package GUI;

import java.awt.Color;
import java.awt.Graphics2D;

public class FoodGui {
    public FoodGui() {
    }

    public void paint(Graphics2D g, int x, int y) {
        g.setColor(new Color(43623));
        g.fillRect(x, y, 7, 7);
    }
}
