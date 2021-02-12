package Game.Organisms;

public class RandomMovement extends Thread {

    private static final int UPDATE_FRAME_RATE = 5;
    private final int millis;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    RandomMovement() {
        this.millis = 1000 / UPDATE_FRAME_RATE;
    }

    public void run() {
        while (true) {
            up = Math.random() > 0.5;
            down = Math.random() > 0.5;
            right = Math.random() > 0.5;
            left = Math.random() > 0.5;

            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}