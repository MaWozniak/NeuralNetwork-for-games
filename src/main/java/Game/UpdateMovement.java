package Game;

public class UpdateMovement extends Thread {

    private int millis;
    private int direction = 0;
    private double acceleration = 0.0;

    UpdateMovement(int framerate) {
        this.millis = 1000 / framerate;
    }

    public void run() {

        while (true) {

            direction = (int) (20 * Math.random() - 10.0);
            acceleration = 0.8 * ((Math.random() / 5) - 0.1);

            //System.out.println(direction);

            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    int getDirection() {
        return direction;
    }

    double getAcceleration() {
        return acceleration;
    }


}