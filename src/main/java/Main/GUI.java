package Main;

import javax.swing.*;

public class GUI extends JFrame {

    GUI(int framerate, Biom biom) throws InterruptedException {

        RenderPanel renderPanel = new RenderPanel(biom);
        this.add(renderPanel);
        this.setSize(1200, 830);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int millis = 1000 / framerate;

        while (true) {

            renderPanel.revalidate();
            renderPanel.repaint();

            Thread.sleep(millis);

        }
    }
}