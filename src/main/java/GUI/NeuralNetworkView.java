package GUI;

import java.awt.*;

public class NeuralNetworkView {

    public NeuralNetworkView() {
    }

    public void paint(Graphics2D g, double x, double y, double angle, double energy, double[] inputs, double[] outputs) {

        g.setColor(Color.BLACK);
        g.setFont(new Font("Ubuntu", Font.PLAIN, 14));

        for (int i = 0; i < inputs.length; i++) {
            g.setColor(Color.DARK_GRAY);
            if (i < 3) {
                g.drawString("bo", 1050, 350 + i * 12);
            }
            g.setColor(Color.BLUE);
            if ((i >= 3) && (i < 11)) {
                g.drawString("Pr", 1050, 350 + i * 12);
            }
            g.setColor(Color.DARK_GRAY);
            if ((i >= 11) && (i < 19)) {
                g.drawString("ap", 1050, 350 + i * 12);
            }
            g.setColor(Color.BLUE);
            if ((i >= 19) && (i < 27)) {
                g.drawString("Hi", 1050, 350 + i * 12);
            }
            g.setColor(Color.DARK_GRAY);
            if ((i >= 27) && (i < 35)) {
                g.drawString("fd", 1050, 350 + i * 12);
            }
            g.setColor(Color.BLUE);
            if ((i == 35)) {
                g.drawString("E", 1050, 350 + i * 12);
            }
            g.setColor(Color.DARK_GRAY);
            if ((i == 36)) {
                g.drawString("b", 1050, 350 + i * 12);
            }
            g.setColor(Color.BLACK);
        }

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] > 0.1) {
                g.setColor(Color.RED);
            }
            g.drawString(String.valueOf((int) inputs[i]), 1072, 350 + i * 12);

            g.setColor(Color.BLACK);
        }

        for (int i = 0; i < outputs.length; i++) {
            if (outputs[i] > 0.1) {
                g.setColor(Color.RED);
            }
            g.drawString(String.valueOf((int) outputs[i]), 1092, 560 + i * 12);

            g.setColor(Color.BLACK);
        }
    }
}
