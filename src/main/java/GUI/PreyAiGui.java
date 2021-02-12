package GUI;

import java.awt.Color;
import java.awt.Graphics2D;

public class PreyAiGui {
    public PreyAiGui() {
    }

    void paintViewPoint(Graphics2D g, double FRONT_TRANSALTE, double SIDE_TRANSALTE, int organismSize, int pointSize, double x, double y, double angle) {
        g.fillOval(((int) x - organismSize / 2) + (int) (SIDE_TRANSALTE * Math.cos(angle) + (int) (FRONT_TRANSALTE * Math.sin(angle))),
                ((int) y - organismSize / 2) + (int) (SIDE_TRANSALTE * Math.sin(angle) - (int) (FRONT_TRANSALTE * Math.cos(angle))),
                pointSize,
                pointSize);
    }

    void paintRectangleView(Graphics2D g, boolean TRIANGLE, int yStartPoint, int xStartPoint, int yNumPoints, int xNumPoints, double FRONT_TRANSALTE, double SIDE_TRANSALTE, int organismSize, int pointSize, double x, double y, double angle) {
        int triangle = 0;
        for (int i = yStartPoint; i < yNumPoints; i++) {
            if (TRIANGLE) {
                triangle = i;
            }
            for (int j = xStartPoint; j < xNumPoints - triangle; j++) {

                paintViewPoint(g, i * (FRONT_TRANSALTE / yNumPoints), j * (SIDE_TRANSALTE / xNumPoints), organismSize, pointSize, x, y, angle);
            }
        }

    }

    public void paint(Graphics2D g, double x, double y, double angle, double energy, boolean firstInGen) {
        g.setColor(Color.BLUE);
        if (firstInGen) {
            g.setColor(Color.GREEN);
        }
        g.fillOval((int) x - 6, (int) y - 6, 12, 12);
        //direction point:
        g.setColor(Color.CYAN);
        if (firstInGen) {
            g.setColor(Color.RED);
        }
        //x = x * 50 * Math.cos; y = y * 50 * Math.sin
        paintViewPoint(g, 155.0, 0.0, 12, 5, x, y, angle);
        paintViewPoint(g, 80.0, 90.0, 12, 5, x, y, angle);
        paintViewPoint(g, 80.0, -90.0, 12, 5, x, y, angle);

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
