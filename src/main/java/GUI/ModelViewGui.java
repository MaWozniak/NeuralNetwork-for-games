package GUI;

import javax.swing.*;
import java.awt.*;

public class ModelViewGui extends JFrame {

    private char[][] model;

    public ModelViewGui(boolean visible, char[][] model) {

        this.model = model;

        JPanel jPanel = new JPanel();
        this.add(jPanel);
        jPanel.setLocation(10, 10);
        this.setSize(650, 500);
        this.setLocation(1250, 200);
        this.setVisible(visible);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void paint(Graphics g2) {
        super.paint(g2);
        Graphics2D g2d = (Graphics2D) g2;
        //     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        //          RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setBackground(Color.BLACK);

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, 600, 400);

        g2d.setColor(Color.BLUE);
        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                if (model[i][j] == 'P') {
                    g2d.fillRect(i / 2, j / 2, 1, 1);
                }
            }
        }

        g2d.setColor(Color.ORANGE);
        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                if (model[i][j] == 'X') {
                    g2d.fillRect(i / 2, j / 2, 1, 1);
                }
            }
        }
    }

}
