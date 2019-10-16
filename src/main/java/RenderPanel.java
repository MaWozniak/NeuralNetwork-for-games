import java.awt.*;
import javax.swing.JPanel;

public class RenderPanel extends JPanel {

    private Biom biom;

    RenderPanel(Biom biom) throws InterruptedException {
        this.biom = biom;
    }

    void update() {

        biom.organismsMoves();
        biom.randomAddPrey();
        //biom.randomKillPrey();
        biom.validate();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //g2d.setColor(new Color(20, 20, 20, 50));
        g2d.setBackground(Color.BLACK);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 1200, 800);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(20, 20, 1160, 760);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(180, 20, 820, 760);
        g2d.setColor(Color.BLUE);
        g2d.fillRect(400, 500, 50, 180);
        g2d.fillRect(700, 120, 50, 180);

        //g2d.setColor(Color.GRAY);

        biom.paint(g2d);

    }

}