package GUI;

import java.awt.*;

public class PreyAiGui {

    public PreyAiGui() {
    }

    public void paint(Graphics2D g, double x, double y, double angle, double energy, boolean firstInGen) {

        g.setColor(Color.BLUE);
        if (firstInGen) {
            g.setColor(Color.GREEN);
        }
        //g.setColor(Color.GREEN);
        g.fillOval((int) x - 8, (int) y - 8, 12, 12);

        //temp direction point:
        g.setColor(Color.CYAN);
        if (firstInGen) {
            g.setColor(Color.RED);
        }
        //x = x * 50 * Math.cos; y = y * 50 * Math.sin
        g.fillOval(((int) x - 5) + (int) (155.0 * Math.sin(angle)), ((int) y - 5) - (int) (155.0 * Math.cos(angle)), 5, 5);


        g.fillOval(((int) x - 5) + (int) (90.0 * Math.cos(angle) + (int) (80.0 * Math.sin(angle))),
                ((int) y - 5) + (int) (90.0 * Math.sin(angle) - (int) (80.0 * Math.cos(angle))),
                5,
                5);
        g.fillOval(((int) x - 5) - (int) (90.0 * Math.cos(angle) - (int) (80.0 * Math.sin(angle))),
                ((int) y - 5) - (int) (90.0 * Math.sin(angle) + (int) (80.0 * Math.cos(angle))),
                5,
                5);


        //all point of view:

        /*
        g.setColor(Color.RED);
        g.fillOval(((int) x - 5) + (int) (6.0 * Math.sin(angle)), ((int) y - 5) - (int) (6.0 * Math.cos(angle)), 5, 5);

        g.fillOval(((int) x - 5) + (int) (18.0 * Math.cos(angle) - (int) (18.0 * Math.sin(angle))),
                ((int) y - 5) + (int) (18.0 * Math.sin(angle) + (int) (18.0 * Math.cos(angle))),
                5,
                5);
        g.fillOval(((int) x - 5) - (int) (18.0 * Math.cos(angle) + (int) (18.0 * Math.sin(angle))),
                ((int) y - 5) - (int) (18.0 * Math.sin(angle) - (int) (18.0 * Math.cos(angle))),
                5,
                5);
        g.fillOval(((int) x - 5) + (int) (20.0 * Math.cos(angle) + (int) (25.0 * Math.sin(angle))),
                ((int) y - 5) + (int) (20.0 * Math.sin(angle) - (int) (25.0 * Math.cos(angle))),
                5,
                5);
        g.fillOval(((int) x - 5) - (int) (20.0 * Math.cos(angle) - (int) (25.0 * Math.sin(angle))),
                ((int) y - 5) - (int) (20.0 * Math.sin(angle) + (int) (25.0 * Math.cos(angle))),
                5,
                5);
        g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(angle) + (int) (10.0 * Math.sin(angle))),
                ((int) y - 5) + (int) (40.0 * Math.sin(angle) - (int) (10.0 * Math.cos(angle))),
                5,
                5);
        g.fillOval(((int) x - 5) - (int) (40.0 * Math.cos(angle) - (int) (10.0 * Math.sin(angle))),
                ((int) y - 5) - (int) (40.0 * Math.sin(angle) + (int) (10.0 * Math.cos(angle))),
                5,
                5);
         */

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
