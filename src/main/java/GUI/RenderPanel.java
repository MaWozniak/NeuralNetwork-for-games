package GUI;

import Game.Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.JPanel;


public class RenderPanel extends JPanel {
    private Game game;
    private BoardGui boardGui = new BoardGui();
    private GenerationsChartView chartView = new GenerationsChartView();
    private boolean chartViewFlag = false;

    RenderPanel(Game game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        boardGui.setHidePlacesCenter(game.getStage().isHidePlacesCenter());
        boardGui.setHidePlacesBorder(game.getStage().isHidePlacesBorder());
        boardGui.paint(g2d);
        game.paint(g2d);
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