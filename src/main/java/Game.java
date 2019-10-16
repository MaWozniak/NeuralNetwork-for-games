import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

    private OrganismsGenerator generator = new OrganismsGenerator(100, 2);

    private Game() throws InterruptedException {
    }

    private void move() {

        generator.move();
        generator.randomAddPrey();
        //generator.randomKillPrey();
        generator.validate();
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

        generator.paint(g2d);

    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Game of life");
        Game game = new Game();
        frame.add(game);
        frame.setSize(1200, 830);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {

            game.move();
            game.revalidate();
            game.repaint();

            Thread.sleep(33);
            //300FPS~ 3 - good for fast simulations
            //30FPS~ 33
            //60FPS~ 17

        }
    }
}