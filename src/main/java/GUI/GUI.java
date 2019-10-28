package GUI;

import Game.Biom;
import Game.FramerateCount;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI(int framerate, Biom biom) throws InterruptedException {

        RenderPanel renderPanel = new RenderPanel(biom);
        JPanel buttonPanel = new JPanel();
        FramerateCount framerateCount = new FramerateCount();
        JLabel label = new JLabel(Integer.toString(biom.getFramerate()));
        JButton button1 = new JButton("Slower");
        button1.addActionListener(actionEvent1 -> {
            biom.setFramerate(framerateCount.previous());
            label.setText(framerateCount.getSelectedInString());
        });
        JButton button2 = new JButton("Faster");
        button2.addActionListener(actionEvent2 -> {
            biom.setFramerate(framerateCount.next());
            label.setText(framerateCount.getSelectedInString());
        });

        buttonPanel.setSize(100, 30);
        buttonPanel.add(button1);
        buttonPanel.add(label);
        buttonPanel.add(button2);

        this.setLayout(new BorderLayout());
        this.add(renderPanel);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1200, 860);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int millis = 1000 / framerate;

        while (true) {

            renderPanel.revalidate();
            renderPanel.repaint();

            Thread.sleep(millis);

        }
    }
}