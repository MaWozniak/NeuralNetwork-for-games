import java.awt.*;
import javax.swing.JPanel;

public class RenderPanel extends JPanel {

    private Biom biom;
    private BoardGui boardGui = new BoardGui();

    RenderPanel(Biom biom) {
        this.biom = biom;
    }

    void update() {

        //biom.lifecycle();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        boardGui.paint(g2d);
        biom.paint(g2d);

    }

}