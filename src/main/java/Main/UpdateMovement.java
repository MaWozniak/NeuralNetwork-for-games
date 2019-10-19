package Main;

public class UpdateMovement extends Thread {

    private int millis;

    UpdateMovement(int framerate) {
        this.millis = 1000 / framerate;
    }

    int getDirection() {
        return direction;
    }

    double getAcceleration() {
        return acceleration;
    }

    private int direction = 0;
    private double acceleration = 0.0;

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

}