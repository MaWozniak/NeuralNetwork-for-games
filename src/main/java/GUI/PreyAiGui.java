package GUI;

import java.awt.*;

public class PreyAiGui {

    public PreyAiGui() {
    }

    public void paint(Graphics2D g, double x, double y, double radians, double energy) {

        g.setColor(Color.BLUE);
        //g.setColor(Color.GREEN);
        g.fillOval((int) x - 8, (int) y - 8, 12, 12);

        //temp direction point:
        g.setColor(Color.CYAN);
        //x = x * 50 * Math.cos; y = y * 50 * Math.sin
        g.fillOval(((int) x - 5) + (int) (55.0 * Math.cos(radians)), ((int) y - 5) + (int) (55.0 * Math.sin(radians)), 5, 5);


        g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(radians) - (int) (50.0 * Math.sin(radians))),
                ((int) y - 5) + (int) (40.0 * Math.sin(radians) + (int) (50.0 * Math.cos(radians))),
                5,
                5);
        g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(radians) + (int) (50.0 * Math.sin(radians))),
                ((int) y - 5) + (int) (40.0 * Math.sin(radians) - (int) (50.0 * Math.cos(radians))),
                5,
                5);
        /*

        all point of view:

        g.setColor(Color.RED);
        g.fillOval(((int) x - 5) + (int) (8.0 * Math.cos(radians)), ((int) y - 5) + (int) (8.0 * Math.sin(radians)), 5, 5);
        g.fillOval(((int) x - 5) - (int) (14.0 * Math.cos(radians) + (int) (14.0 * Math.sin(radians))),
                ((int) y - 5) - (int) (14.0 * Math.sin(radians) - (int) (14.0 * Math.cos(radians))),
                5,
                5);
        g.fillOval(((int) x - 5) - (int) (14.0 * Math.cos(radians) - (int) (14.0 * Math.sin(radians))),
                ((int) y - 5) - (int) (14.0 * Math.sin(radians) + (int) (14.0 * Math.cos(radians))),
                5,
                5);
        g.fillOval(((int) x - 5) + (int) (25.0 * Math.cos(radians) + (int) (20.0 * Math.sin(radians))),
                ((int) y - 5) + (int) (25.0 * Math.sin(radians) - (int) (20.0 * Math.cos(radians))),
                5,
                5);
        g.fillOval(((int) x - 5) + (int) (25.0 * Math.cos(radians) - (int) (20.0 * Math.sin(radians))),
                ((int) y - 5) + (int) (25.0 * Math.sin(radians) + (int) (20.0 * Math.cos(radians))),
                5,
                5);
        g.fillOval(((int) x - 5) + (int) (10.0 * Math.cos(radians) + (int) (40.0 * Math.sin(radians))),
                ((int) y - 5) + (int) (10.0 * Math.sin(radians) - (int) (40.0 * Math.cos(radians))),
                5,
                5);
        g.fillOval(((int) x - 5) + (int) (10.0 * Math.cos(radians) - (int) (40.0 * Math.sin(radians))),
                ((int) y - 5) + (int) (10.0 * Math.sin(radians) + (int) (40.0 * Math.cos(radians))),
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
