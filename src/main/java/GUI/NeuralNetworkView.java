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
            if (i == 0) {
                g.drawString("bF", 1050, 350 + i * 12);
            }
            if (i == 1) {
                g.drawString("bN", 1050, 350 + i * 12);
            }
            g.setColor(Color.BLUE);
            if (i == 2) {
                g.drawString("FL", 1050, 350 + i * 12);
            }
            if (i == 3) {
                g.drawString("FF", 1050, 350 + i * 12);
            }
            if (i == 4) {
                g.drawString("FR", 1050, 350 + i * 12);
            }
            if (i == 5) {
                g.drawString("FC", 1050, 350 + i * 12);
            }
            if (i == 6) {
                g.drawString("FiP", 1050, 350 + i * 12);
            }
            g.setColor(Color.DARK_GRAY);
            if (i == 7) {
                g.drawString("aL", 1050, 350 + i * 12);
            }
            if (i == 8) {
                g.drawString("aF", 1050, 350 + i * 12);
            }
            if (i == 9) {
                g.drawString("aR", 1050, 350 + i * 12);
            }
            g.setColor(Color.BLUE);
            if (i == 10) {
                g.drawString("PrL", 1050, 350 + i * 12);
            }
            if (i == 11) {
                g.drawString("PrF", 1050, 350 + i * 12);
            }
            if (i == 12) {
                g.drawString("PrR", 1050, 350 + i * 12);
            }
            if (i == 13) {
                g.drawString("PrN", 1050, 350 + i * 12);
            }
            if (i == 14) {
                g.drawString("PrC", 1050, 350 + i * 12);
            }
            if (i == 15) {
                g.drawString("PLB", 1050, 350 + i * 12);
            }
            if (i == 16) {
                g.drawString("PB", 1050, 350 + i * 12);
            }
            if (i == 17) {
                g.drawString("PRB", 1050, 350 + i * 12);
            }
            g.setColor(Color.DARK_GRAY);
            if (i == 18) {
                g.drawString("hF", 1050, 350 + i * 12);
            }
            if (i == 19) {
                g.drawString("hiP", 1050, 350 + i * 12);
            }
            g.setColor(Color.BLUE);
            if (i == 20) {
                g.drawString("spe", 1050, 350 + i * 12);
            }
            if (i == 21) {
                g.drawString("ene", 1050, 350 + i * 12);
            }
            if (i == 22) {
                g.drawString("sBi", 1050, 350 + i * 12);
            }
        }

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] > 0.1) {
                g.setColor(Color.RED);
            }
            g.drawString(String.valueOf((int) inputs[i]), 1078, 350 + i * 12);

            g.setColor(Color.BLACK);
        }

        for (int i = 0; i < outputs.length; i++) {
            if (outputs[i] > 0.1) {
                g.setColor(Color.RED);
            }
            g.drawString(String.valueOf((int) outputs[i]), 1092, 464 + i * 12);

            g.setColor(Color.BLACK);
        }
    }
}
