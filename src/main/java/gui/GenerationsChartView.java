package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GenerationsChartView {
    private final static boolean TEST = false;
    private final static int HEIGHT_OF_CHART_PLOTS = 80;
    private static final DecimalFormat df2 = new DecimalFormat("#.##");
    private List<List<Double>> generationsScoresList = new ArrayList<>();
    private List<List<Double>> generationsAverageList = new ArrayList<>();

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

        delta = 1050 / (generationsScoresList.get(0).size() + 1);
        int step = 1;
        if (generationsScoresList.get(0).size() > 900) {
            delta = 1;
            step = 1 + generationsScoresList.get(0).size() / 1000;
        }

        int valueStep = 1;
        if (generationsScoresList.get(0).size() > 20) {
            valueStep = generationsScoresList.get(0).size() / 10;
        }

        int verticalFactor;

        g.setColor(Color.BLUE);
        for (int i = 0; i < generationsAverageList.get(0).size(); i += step) {
            double mixedValue = mixedValue(generationsScoresList.get(0).get(i), generationsAverageList.get(0).get(i));
            verticalFactor = (int) (HEIGHT_OF_CHART_PLOTS * mixedValue);
            g.fillOval(100 + i / step * delta, (int) (500 - verticalFactor), 4, 4);
            if (i % valueStep == 0) {
                g.drawString(df2.format(generationsAverageList.get(0).get(i)), 100 + i / step * delta, 540);
            }

        }

        g.setColor(Color.YELLOW);

        for (int i = 0; i < generationsScoresList.get(1).size(); i += step) {
            double mixedValue = mixedValue(generationsScoresList.get(1).get(i), generationsAverageList.get(1).get(i));
            verticalFactor = (int) (HEIGHT_OF_CHART_PLOTS * mixedValue);
            g.fillOval(100 + i / step * delta, (int) (502 - verticalFactor), 4, 4);
            if (i % valueStep == 0) {
                g.drawString(df2.format(mixedValue), 100 + i / step * delta, 552);
            }

        }

        g.setColor(Color.GREEN);

        for (int i = 0; i < generationsScoresList.get(2).size(); i += step) {
            double mixedValue = mixedValue(generationsScoresList.get(2).get(i), generationsAverageList.get(2).get(i));
            verticalFactor = (int) (HEIGHT_OF_CHART_PLOTS * mixedValue);
            g.fillOval(100 + i / step * delta, (int) (502 - verticalFactor), 4, 4);
           if (i % valueStep == 0) {
                g.drawString(df2.format(mixedValue), 100 + i / step * delta, 564);
            }

        }

        g.setColor(Color.MAGENTA);

        for (int i = 0; i < generationsScoresList.get(3).size(); i += step) {
            double mixedValue = mixedValue(generationsScoresList.get(3).get(i), generationsAverageList.get(3).get(i));
            verticalFactor = (int) (HEIGHT_OF_CHART_PLOTS * mixedValue);
            g.fillOval(100 + i / step * delta, (int) (502 - verticalFactor), 4, 4);
            if (i % valueStep == 0) {
                g.drawString(df2.format(mixedValue), 100 + i / step * delta, 576);
            }

        }

        g.setColor(Color.BLACK);

        for (int i = 0; i < generationsScoresList.get(4).size(); i += step) {
            double mixedValue = mixedValue(generationsScoresList.get(4).get(i), generationsAverageList.get(4).get(i));
            verticalFactor = (int) (HEIGHT_OF_CHART_PLOTS * mixedValue);
            g.fillOval(100 + i / step * delta, (int) (502 - verticalFactor), 4, 4);
            if (i % valueStep == 0) {
                g.drawString(df2.format(mixedValue), 100 + i / step * delta, 588);
            }

        }

        String text = "num of generations: " + df2.format(generationsScoresList.get(0).size());
        g.drawString(text, 540, 595);

    }

    public void getData(List<List<Double>> generationsScoresList, List<List<Double>> generationsAverageList) {
        this.generationsScoresList = generationsScoresList;
        this.generationsAverageList = generationsAverageList;
    }

    public void test() {
        generationsScoresList.clear();
        generationsAverageList.clear();
        int size = 900;

        for (int i = 0; i < size; i++) {
            generationsScoresList.get(0).add(40 * Math.random());
            generationsAverageList.get(0).add(i / 10 + Math.random());
        }

    }

    private Double mixedValue(Double one, Double two) {
        int scaleOne = 1;
        int scaleTwo = 4;
        return (one * scaleOne + two * scaleTwo) / (scaleOne + scaleTwo);
    }

}
