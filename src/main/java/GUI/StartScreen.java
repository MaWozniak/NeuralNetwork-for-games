package GUI;

import Game.Biom;
import Game.ModelView;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {
    public StartScreen() {

        JPanel buttonPanel = new JPanel();
        JLabel label = new JLabel(Integer.toString(100));
        JButton button1 = new JButton("Start");
        button1.addActionListener(actionEvent1 -> {
            this.dispose();
            try {
                runSimulation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        buttonPanel.setSize(100, 30);
        buttonPanel.add(button1);
        buttonPanel.add(label);

        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1200, 860);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void runSimulation() throws InterruptedException {

        ModelView model = new ModelView(false);
        Biom biom = new Biom(0, 3, 50, 30, false, model, 15,
                true, false, true, false, true, 250, 90, 45);
        new GUI(30, biom);
    }
}