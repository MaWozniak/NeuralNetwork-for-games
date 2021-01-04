package GUI;

import java.awt.Color;
import java.awt.Graphics2D;

public class BoardGui {

    public BoardGui() {
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1200, 800);
//        g.setColor(Color.LIGHT_GRAY);
//        g.fillRect(20, 20, 1160, 760);
//        g.setColor(Color.WHITE);
//        g.fillRect(180, 20, 820, 760);
        g.setColor(Color.WHITE);
        g.fillRect(20, 20, 1160, 760);
        g.setColor(Color.lightGray);
        g.fillRect(400, 270, 400, 280);
    }
}
