package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class GenerationsChartView {
    private final static boolean TEST = false;
    private int HEIGHT_OF_CHART_PLOTS = 64;
    private static final DecimalFormat df2 = new DecimalFormat("#.##");
    private List<List<Double>> generationsScoresList = new LinkedList<>();
    private List<List<Double>> generationsAverageList = new LinkedList<>();

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

        List<Color> colors = fillColors();

        for(int j = 0; j < generationsAverageList.size(); j++) {
            g.setColor(colors.get(j % colors.size()));
            for (int i = 0; i < generationsAverageList.get(j).size(); i += step) {
                double mixedValue = mixedValue(generationsScoresList.get(j).get(i), generationsAverageList.get(j).get(i));
                verticalFactor = (int) (HEIGHT_OF_CHART_PLOTS * mixedValue);
                g.fillOval(100 + i / step * delta, (int) (500 + j*2 - verticalFactor), 4, 4);
                if (i % valueStep == 0) {
                    g.drawString(df2.format(generationsAverageList.get(j).get(i)), 100 + i / step * delta, 540 + j*11);
                }

            }
        }

        g.setColor(Color.YELLOW);
        String text = "num of generations: " + df2.format(generationsScoresList.get(0).size());
        g.drawString(text, 540, 670);

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

    public void scaleDownChart() {
        if(HEIGHT_OF_CHART_PLOTS > 2) {
            HEIGHT_OF_CHART_PLOTS = HEIGHT_OF_CHART_PLOTS / 2;
        } else {
            HEIGHT_OF_CHART_PLOTS = 1;
        }
    }

    public void scaleUpChart() {
        if(HEIGHT_OF_CHART_PLOTS  < 512) {
            HEIGHT_OF_CHART_PLOTS = HEIGHT_OF_CHART_PLOTS * 2;
        } else {
            HEIGHT_OF_CHART_PLOTS = 512;
        }
    }

    private Double mixedValue(Double one, Double two) {
        int scaleOne = 1;
        int scaleTwo = 4;
        return (one * scaleOne + two * scaleTwo) / (scaleOne + scaleTwo);
    }

    private List<Color> fillColors() {
        List<Color> colors = new LinkedList<>();
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);
        colors.add(Color.ORANGE);
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(Color.CYAN);
        colors.add(Color.PINK);
        colors.add(Color.RED);
        return colors;
    }

}
