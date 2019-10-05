import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BallsGenerator {

    private List<Ball> generatedBalls = new ArrayList<>();

    public List<Ball> getGeneratedBalls() {
        return generatedBalls;
    }

    BallsGenerator(int number) {

        for (int i = 0; i < number; i++) {

            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();
            int id = (int) (1000 * Math.random());
            Ball newBall = new Ball(xStartPos, yStartPos, id);

            generatedBalls.add(newBall);
        }

    }

    void move() {

        for (Ball generatedBall : generatedBalls) {
            generatedBall.move();
        }
    }

    void paint(Graphics2D g) {

        for (Ball generatedBall : generatedBalls) {
            generatedBall.paint(g);
        }
    }
}
