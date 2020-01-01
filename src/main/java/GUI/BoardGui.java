package GUI;

import java.awt.*;

public class BoardGui {

    public BoardGui() {
    }

    public void paint(Graphics2D g) {

        //g.setColor(new Color(20, 20, 20, 50));
        //g.setBackground(Color.BLACK);
        g.setColor(Color.BLACK);
        //g.setColor(new Color(5, 5, 5, 50));
        g.fillRect(0, 0, 1200, 800);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(20, 20, 1160, 760);
        g.setColor(Color.WHITE);
        g.fillRect(180, 20, 820, 760);
        g.setColor(new Color(35114));
        g.fillRect(400, 500, 50, 180);
        g.fillRect(700, 120, 50, 180);

        //g.setColor(Color.GRAY);
    }
}
