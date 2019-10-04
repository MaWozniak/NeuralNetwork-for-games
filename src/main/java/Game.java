import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

    //add a list to handle a number of balls
    private Ball ball = new Ball( 250.0, 190.0, "b001");
    private Ball ball1 = new Ball( 450.0, 400.0, "a000");

    private void move() {

        ball.move();
        ball1.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        ball.paint(g2d);
        ball1.paint(g2d);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("title");
        Game game = new Game();
        frame.add(game);
        frame.setSize(900, 700);
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