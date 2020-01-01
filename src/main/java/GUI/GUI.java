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
        JButton button3 = new JButton("Progres Chart");
        button3.addActionListener(actionEvent3 -> {
            renderPanel.getGenerationMemory(biom.getGenerationsScores(), biom.getGenerationsAverageScores());
            renderPanel.setChartViewFlag();
        });

        JLabel label1 = new JLabel("Predators:");
        JLabel label2 = new JLabel(Integer.toString(biom.getPredatorsNumber()));
        JButton button4 = new JButton("-");
        button4.addActionListener(actionEvent1 -> {
            biom.removePredator();
            label2.setText(Integer.toString(biom.getPredatorsNumber()));
        });
        JButton button5 = new JButton("+");
        button5.addActionListener(actionEvent2 -> {
            biom.addPredator();
            label2.setText(Integer.toString(biom.getPredatorsNumber()));
        });

        JLabel label3 = new JLabel(biom.getMutationRate());
        JButton button6 = new JButton("Get Mutation rate:");
        button6.addActionListener(actionEvent2 -> {
            label3.setText(biom.getMutationRate());
        });


        buttonPanel.setSize(100, 30);
        buttonPanel.add(button1);
        buttonPanel.add(label);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(label1);
        buttonPanel.add(button4);
        buttonPanel.add(label2);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(label3);

        this.setLayout(new BorderLayout());
        this.add(renderPanel);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1200, 860);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int millis = 1000 / framerate;

        while (true) {

            renderPanel.revalidate();
            renderPanel.repaint();

            Thread.sleep(millis);

        }
    }
}