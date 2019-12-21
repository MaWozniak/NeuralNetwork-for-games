package GUI;

import java.awt.*;

public class PredatorGui {

    public PredatorGui() {
    }

    public void paint(Graphics2D g, double x, double y, double angle, double energy) {

        g.setColor(Color.RED);
        //g.setColor(Color.YELLOW);
        g.fillOval((int) x - 10, (int) y - 10, 20, 20);

        //temp direction point:
        g.setColor(Color.BLUE);
        g.fillOval(((int) x - 5) + (int) (90.0 * Math.sin(angle)), ((int) y - 5) - (int) (90.0 * Math.cos(angle)), 5, 5);
        g.fillOval(((int) x - 5) + (int) (50.0 * Math.cos(angle) + (int) (70.0 * Math.sin(angle))),
                ((int) y - 5) + (int) (50.0 * Math.sin(angle) - (int) (70.0 * Math.cos(angle))), 5, 5);
        g.fillOval(((int) x - 5) - (int) (50.0 * Math.cos(angle) - (int) (70.0 * Math.sin(angle))),
                ((int) y - 5) - (int) (50.0 * Math.sin(angle) + (int) (70.0 * Math.cos(angle))), 5, 5);
        g.fillOval(((int) x - 5) + (int) (55.0 * Math.sin(angle)), ((int) y - 5) - (int) (55.0 * Math.cos(angle)), 5, 5);

        g.fillOval(((int) x - 5) + (int) (80.0 * Math.cos(angle) + (int) (35.0 * Math.sin(angle))),
                ((int) y - 5) + (int) (80.0 * Math.sin(angle) - (int) (35.0 * Math.cos(angle))), 5, 5);
        g.fillOval(((int) x - 5) - (int) (80.0 * Math.cos(angle) - (int) (35.0 * Math.sin(angle))),
                ((int) y - 5) - (int) (80.0 * Math.sin(angle) + (int) (35.0 * Math.cos(angle))), 5, 5);
        g.fillOval(((int) x - 5) + (int) (35.0 * Math.cos(angle) + (int) (30.0 * Math.sin(angle))),
                ((int) y - 5) + (int) (35.0 * Math.sin(angle) - (int) (30.0 * Math.cos(angle))), 5, 5);
        g.fillOval(((int) x - 5) - (int) (35.0 * Math.cos(angle) - (int) (30.0 * Math.sin(angle))),
                ((int) y - 5) - (int) (35.0 * Math.sin(angle) + (int) (30.0 * Math.cos(angle))), 5, 5);

        g.setColor(Color.DARK_GRAY);
        if (energy < 60) {
            g.setColor(Color.GRAY);
        }
        if (energy < 30) {
            g.setColor(Color.RED);
        }
        g.drawString(String.valueOf((int) energy), (int) x - 8, (int) y - 8);
    }
}
