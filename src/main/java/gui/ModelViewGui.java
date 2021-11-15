package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ModelViewGui extends JFrame {
    private final byte[][] model;

    public ModelViewGui(boolean visible, byte[][] model) {
        this.model = model;
        JPanel jPanel = new JPanel();
        this.add(jPanel);
        jPanel.setLocation(10, 10);
        this.setSize(600, 400);
        this.setLocation(1250, 200);
        this.setVisible(visible);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g2) {
        super.paint(g2);
        Graphics2D g2d = (Graphics2D) g2;
//             g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                  RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setBackground(Color.BLACK);
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, 600, 400);

        //refactor method from:
        g2d.setColor(Color.DARK_GRAY);

        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                if (model[i][j] == 1) {
                    g2d.fillRect(i / 2, j / 2, 1, 1);
                }
            }
        }

        g2d.setColor(Color.BLUE);

        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                if (model[i][j] == 3) {
                    g2d.fillRect(i / 2, j / 2, 1, 1);
                }
            }
        }

        g2d.setColor(Color.RED);

        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                if (model[i][j] == 4) {
                    g2d.fillRect(i / 2, j / 2, 1, 1);
                }
            }
        }

        g2d.setColor(Color.YELLOW);

        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                if (model[i][j] == 2) {
                    g2d.fillRect(i / 2, j / 2, 1, 1);
                }
            }
        }
    }

}
