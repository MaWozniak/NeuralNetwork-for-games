package GUI;

import Game.Biom;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class RenderPanel extends JPanel {

    private Biom biom;
    private BoardGui boardGui = new BoardGui();
    private GenerationsChartView chartView = new GenerationsChartView();
    private boolean chartViewFlag = false;

    RenderPanel(Biom biom) {
        this.biom = biom;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        boardGui.paint(g2d);
        biom.paint(g2d);
        if (chartViewFlag) {
            chartView.paint(g2d);
        }
    }

    public void setChartViewFlag() {
        chartViewFlag = !chartViewFlag;
    }

    public void getGenerationMemory(List<Double> generationsScoresList, List<Double> generationsAverageList) {
        chartView.getData(generationsScoresList, generationsAverageList);
    }

}