import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

    //add a list to handle a number of balls
    private Ball ball = new Ball( 250.0, 190.0, "b001");
    private Ball ball1 = new Ball( 450.0, 400.0, "a000");
    private Ball ball2 = new Ball( 550.0, 290.0, "b003");
    private Ball ball3 = new Ball( 350.0, 270.0, "c011");

    private void move() {

        ball.move();
        ball1.move();
        ball2.move();
        ball3.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //this color manipulation to get motion blur effect:
        //g2d.setColor(new Color(20,20,20,1));
        //g2d.setColor(Color.GRAY);
        //g2d.setColor(new Color(0f, 0f, 0f, (float) 0.5));

        ball.paint(g2d);
        ball1.paint(g2d);
        ball2.paint(g2d);
        ball3.paint(g2d);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("title");
        Game game = new Game();
        frame.add(game);
        frame.setSize(1300, 800);
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