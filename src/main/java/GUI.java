import javax.swing.*;

public class GUI extends JFrame {

    GUI(int framerate, Biom biom) throws InterruptedException {

        RenderPanel renderPanel = new RenderPanel(biom);
        this.add(renderPanel);
        this.setSize(1200, 830);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //30FPS~ 33 - 60FPS~ 17 - 300FPS~ 3 - good for fast simulation
        int millis = (int) (1000 / framerate);

        while (true) {

            renderPanel.update();
            renderPanel.revalidate();
            renderPanel.repaint();

            Thread.sleep(millis);

        }
    }
}