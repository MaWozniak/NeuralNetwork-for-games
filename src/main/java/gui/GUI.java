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
        JLabel frameRateLabel = new JLabel(Integer.toString(game.getFrameRate()));
        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(actionEvent1 -> {
            isSleep = !isSleep;
        });
        JButton slowerButton = new JButton("Slower");
        slowerButton.addActionListener(actionEvent2 -> {
            int actualFrameRate = game.getFrameRate();
            game.setFrameRate(framerateCount.previous(actualFrameRate));
            frameRateLabel.setText(framerateCount.getSelectedInString());
        });
        JButton fasterButton = new JButton("Faster");
        fasterButton.addActionListener(actionEvent2 -> {
            int actualFrameRate = game.getFrameRate();
            game.setFrameRate(framerateCount.next(actualFrameRate));
            frameRateLabel.setText(framerateCount.getSelectedInString());
        });
        JLabel mutationRateLabel = new JLabel(game.getMutationRate());
        JLabel branchNumLabel = new JLabel("BRANCH NUM: " + game.getBranchNumber());
        JButton heightOfChartMinus = new JButton(" - ");
        heightOfChartMinus.addActionListener(actionEvent4 -> {
            renderPanel.scaleDownChartViewHeight();
        });
        heightOfChartMinus.setVisible(false);
        JButton heightOfChartPlus = new JButton(" + ");
        heightOfChartPlus.addActionListener(actionEvent5 -> {
            renderPanel.scaleUpChartViewHeight();
        });
        heightOfChartPlus.setVisible(false);
        JButton progressChartButton = new JButton("Progress Chart");
        progressChartButton.addActionListener(actionEvent3 -> {
            heightOfChartPlus.setVisible(!heightOfChartPlus.isVisible());
            heightOfChartMinus.setVisible(!heightOfChartMinus.isVisible());
            renderPanel.getGenerationMemory(game.getGenerationsScores(), game.getGenerationsAverageScores());
            renderPanel.setChartViewFlag();
        });
        buttonPanel.setSize(100, 30);
        buttonPanel.add(pauseButton);
        buttonPanel.add(slowerButton);
        buttonPanel.add(frameRateLabel);
        buttonPanel.add(fasterButton);
        buttonPanel.add(progressChartButton);
        buttonPanel.add(heightOfChartMinus);
        buttonPanel.add(heightOfChartPlus);
        buttonPanel.add(mutationRateLabel);
        buttonPanel.add(branchNumLabel);
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
                mutationRateLabel.setText("Mutation rate: " + game.getMutationRate());
                branchNumLabel.setText("BRANCH NUM: " + game.getBranchNumber());
            }
            Thread.sleep(millis);
        }
    }

}