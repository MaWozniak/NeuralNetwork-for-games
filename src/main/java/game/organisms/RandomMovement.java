package game.organisms;

public class RandomMovement extends Thread {

    private static final int UPDATE_FRAME_RATE = 5;
    private volatile boolean isAlive = true;
    private final int millis;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    RandomMovement() {
        this.millis = 1000 / UPDATE_FRAME_RATE;
    }

    public void run() {
        while (isAlive) {
            up = Math.random() > 0.5;
            down = Math.random() > 0.5;
            right = Math.random() > 0.5;
            left = Math.random() > 0.5;

            try {
                Thread.sleep(millis);
            } catch (InterruptedException ignored) {
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

    public void kill() {
        isAlive = false;
        this.interrupt();
    }
}