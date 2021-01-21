package GUI;

import Game.Game;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
    public GUI(int framerate, Game game) throws InterruptedException {
        RenderPanel renderPanel = new RenderPanel(game);
        JPanel buttonPanel = new JPanel();
        FrameRateCount framerateCount = new FrameRateCount();
        JLabel label = new JLabel(Integer.toString(game.getFrameRate()));
        JButton button1 = new JButton("Slower");
        button1.addActionListener(actionEvent1 -> {
            game.setFrameRate(framerateCount.previous());
            label.setText(framerateCount.getSelectedInString());
        });
        JButton button2 = new JButton("Faster");
        button2.addActionListener(actionEvent2 -> {
            game.setFrameRate(framerateCount.next());
            label.setText(framerateCount.getSelectedInString());
        });
        JButton button3 = new JButton("Progres Chart");
        button3.addActionListener(actionEvent3 -> {
            renderPanel.getGenerationMemory(game.getGenerationsScores(), game.getGenerationsAverageScores());
            renderPanel.setChartViewFlag();
        });
//        JLabel label1 = new JLabel("Predators:");
//        JLabel label2 = new JLabel(Integer.toString(game.getGameConfig().getNumberOfPredator()));
//        JButton button4 = new JButton("-");
//        button4.addActionListener(actionEvent1 -> {
//            //game.removePredator();
//            label2.setText(Integer.toString(game.getGameConfig().getNumberOfPredator()));
//        });
//        JButton button5 = new JButton("+");
//        button5.addActionListener(actionEvent2 -> {
//            //game.addPredator();
//            label2.setText(Integer.toString(game.getGameConfig().getNumberOfPredator()));
//        });
        JLabel label3 = new JLabel(game.getMutationRate());
        JButton button6 = new JButton("Get Mutation rate:");
        button6.addActionListener(actionEvent2 -> {
            label3.setText(game.getMutationRate());
        });
        buttonPanel.setSize(100, 30);
        buttonPanel.add(button1);
        buttonPanel.add(label);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
//        buttonPanel.add(label1);
//        buttonPanel.add(button4);
//        buttonPanel.add(label2);
//        buttonPanel.add(button5);
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