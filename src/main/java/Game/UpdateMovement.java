package Game;

public class UpdateMovement extends Thread {
    private int millis;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    UpdateMovement(int framerate) {
        this.millis = 1000 / framerate;
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