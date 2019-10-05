import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

    private BallsGenerator generator = new BallsGenerator(50);

    private void move() {

        generator.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(20, 20, 20, 50));
        g2d.fillRect(300, 200, 600, 400);
        g2d.setColor(Color.GRAY);

        generator.paint(g2d);

    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("title");
        Game game = new Game();
        frame.add(game);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UpdateMovement updateMovement = new UpdateMovement();
        updateMovement.start();

        while (true) {

            game.move();
            game.revalidate();
            game.repaint();

            Thread.sleep(50);

        }
    }
}