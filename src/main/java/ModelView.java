import javax.swing.*;
import java.awt.*;

public class ModelView extends JFrame {

    private char[][] model = new char[1300][900];

    public char[][] getModel() {
        return model;
    }

    private JPanel jPanel = new JPanel();

    ModelView() throws InterruptedException {

        this.add(jPanel);
        jPanel.setLocation(10, 10);
        this.setSize(650, 500);
        this.setLocation(1250, 200);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void paint(Graphics g2) {
        super.paint(g2);
        Graphics2D g2d = (Graphics2D) g2;
        //     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        //          RenderingHints.VALUE_ANTIALIAS_ON);

        //g2d.setColor(new Color(20, 20, 20, 50));
        g2d.setBackground(Color.BLACK);

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, 600, 400);

        g2d.setColor(Color.BLUE);
        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                if (model[i][j] == 'P') {
                    g2d.fillRect((int) (i / 2), (int) (j / 2), 1, 1);
                }
            }
        }

        g2d.setColor(Color.ORANGE);
        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                if (model[i][j] == 'X') {
                    g2d.fillRect((int) (i / 2), (int) (j / 2), 1, 1);
                }
            }
        }
    }

    public void clear() {
        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                model[i][j] = '0';
            }
        }
    }

    public void set(int x, int y, char ch) {
        int r = 30;
        for (int a = -r; a < r; a++) {
            for (int b = -r; b < r; b++) {

                if ((x + a) >= 0 && (y + b) >= 0 && (x + a) < 1290 && (y + b) < 890) {
                    model[x + a][y + b] = ch;
                }

            }
        }

    }

}
