package gui;

import game.Game;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
    private static final int FRAME_RATE = 60;
    private static boolean isSleep = false;

    public GUI(Game game) throws InterruptedException {
        RenderPanel renderPanel = new RenderPanel(game);
        JPanel buttonPanel = new JPanel();
        FrameRateCount framerateCount = new FrameRateCount();
        JLabel label = new JLabel(Integer.toString(game.getFrameRate()));
        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(actionEvent1 -> {
            if(!isSleep) {
                isSleep = true;
                game.setFrameRate(1);
                label.setText("1");
            } else {
                isSleep = false;
                game.setFrameRate(9999999);
                label.setText("9999999");
            }
        });
        JButton button1 = new JButton("Slower");
        button1.addActionListener(actionEvent1 -> {
            int actualFrameRate = game.getFrameRate();
            game.setFrameRate(framerateCount.previous(actualFrameRate));
            label.setText(framerateCount.getSelectedInString());
        });
        JButton button2 = new JButton("Faster");
        button2.addActionListener(actionEvent2 -> {
            int actualFrameRate = game.getFrameRate();
            game.setFrameRate(framerateCount.next(actualFrameRate));
            label.setText(framerateCount.getSelectedInString());
        });
        JLabel label3 = new JLabel(game.getMutationRate());
        JLabel label4 = new JLabel("BRANCH NUM: " + game.getBranchNumber());
        JButton button3 = new JButton("Progress Chart");
        button3.addActionListener(actionEvent3 -> {
            renderPanel.getGenerationMemory(game.getGenerationsScores(), game.getGenerationsAverageScores());
            renderPanel.setChartViewFlag();
        });
        buttonPanel.setSize(100, 30);
        buttonPanel.add(pauseButton);
        buttonPanel.add(button1);
        buttonPanel.add(label);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(label3);
        buttonPanel.add(label4);
        this.setLayout(new BorderLayout());
        this.add(renderPanel);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1200, 860);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int millis = 1000 / FRAME_RATE;

        while (true) {

            if(!isSleep) {
                renderPanel.revalidate();
                renderPanel.repaint();
                label3.setText("Mutation rate: " + game.getMutationRate());
                label4.setText("BRANCH NUM: " + game.getBranchNumber());
            }
            Thread.sleep(millis);
        }
    }

}