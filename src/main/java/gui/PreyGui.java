package gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class PreyGui {
    public PreyGui() {
    }

    public void paint(Graphics2D g, double x, double y, double angle, double energy) {
        g.setColor(Color.GRAY);
        g.fillOval((int) x - 8, (int) y - 8, 12, 12);
        //direction point:
        g.setColor(Color.MAGENTA);
        //x = x * 50 * Math.cos; y = y * 50 * Math.sin
        g.fillOval(((int) x - 5) + (int) (50.0 * Math.sin(angle)), ((int) y - 5) - (int) (50.0 * Math.cos(angle)), 5, 5);
        g.fillOval(((int) x - 5) + (int) (10.0 * Math.sin(angle)), ((int) y - 5) - (int) (10.0 * Math.cos(angle)), 5, 5);
        g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(angle) + (int) (20.0 * Math.sin(angle))),
                ((int) y - 5) + (int) (40.0 * Math.sin(angle) - (int) (20.0 * Math.cos(angle))),
                5,
                5);
        g.fillOval(((int) x - 5) - (int) (40.0 * Math.cos(angle) - (int) (20.0 * Math.sin(angle))),
                ((int) y - 5) - (int) (40.0 * Math.sin(angle) + (int) (20.0 * Math.cos(angle))),
                5,
                5);

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
