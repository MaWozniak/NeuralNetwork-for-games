package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GenerationsChartView {
    private final static boolean TEST = false;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private List<Double> generationsScoresList = new ArrayList<>();
    private List<Double> generationsAverageList = new ArrayList<>();

    public GenerationsChartView() {
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(100, 100, 1000, 600);
        g.setColor(Color.GREEN);
        int start = 20;

        int delta;
        for (delta = start; delta < 100; delta++) {
            g.fillOval(delta * 10, 600 + (int) (50 * Math.random()), 6, 6);
        }

        delta = 1050 / (generationsScoresList.size() + 1);
        int step = 1;
        if (generationsScoresList.size() > 900) {
            delta = 1;
            step = 1 + generationsScoresList.size() / 1000;
        }

        int valueStep = 1;
        if (generationsScoresList.size() > 20) {
            valueStep = generationsScoresList.size() / 10;
        }

        int verticalFactor;

        g.setColor(Color.BLUE);
        for (int i = 0; i < generationsAverageList.size(); i += step) {
            verticalFactor = (int) (50 * generationsAverageList.get(i));
            g.fillOval(100 + i / step * delta, (int) (500 - verticalFactor), 4, 4);
            if (i % valueStep == 0) {
                g.drawString(df2.format(generationsAverageList.get(i)), 100 + i / step * delta, 540);
            }

        }

        g.setColor(Color.YELLOW);

        for (int i = 0; i < generationsScoresList.size(); i += step) {
            verticalFactor = (int) (50 * generationsScoresList.get(i));
            g.fillOval(100 + i / step * delta, (int) (502 - verticalFactor), 4, 4);
            if (i % valueStep == 0) {
                g.drawString(df2.format(generationsScoresList.get(i)), 100 + i / step * delta, 550);
            }

        }

        g.drawString("num of generations: " + df2.format(generationsScoresList.size()), 540, 580);

    }

    public void getData(List<Double> generationsScoresList, List<Double> generationsAverageList) {
        this.generationsScoresList = generationsScoresList;
        this.generationsAverageList = generationsAverageList;
    }

    public void test() {
        generationsScoresList.clear();
        generationsAverageList.clear();
        int size = 900;
        //size = (int)(2500*Math.random());

        for (int i = 0; i < size; i++) {
            generationsScoresList.add(40 * Math.random());
            generationsAverageList.add(i / 10 + Math.random());
        }

    }

}
