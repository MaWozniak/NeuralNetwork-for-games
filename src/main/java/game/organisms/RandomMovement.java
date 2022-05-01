package game.organisms;

import java.util.ArrayList;
import java.util.List;

public class RandomMovement extends Thread {

    private static RandomMovement firstInstance = null;

    private static final int UPDATE_FRAME_RATE = 5;
    private static final int NUM_OF_VALUES = 60;
    private volatile boolean isAlive = true;
    private final int millis;
    private final List<Boolean> values = new ArrayList<>();

    public static RandomMovement getInstance() {
        synchronized (RandomMovement.class) {
            if(firstInstance == null) {
                return firstInstance = new RandomMovement();
            } else {
                return firstInstance;
            }
        }

    }

    private RandomMovement() {
        this.millis = 1000 / UPDATE_FRAME_RATE;
        for (int i = 0; i < NUM_OF_VALUES; i++) {
            values.add(false);
        }
        this.start();
    }

    public void run() {
        while (isAlive) {
            for (int i = 0; i < values.size(); i++) {
                if (Math.random() > 0.5) {
                    values.set(i, true);
                } else {
                    values.set(i, false);
                }
            }
            try {
                Thread.sleep(millis);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public boolean get(int i) {
        if(i > NUM_OF_VALUES) {
            return false;
        } else {
            return values.get(i);
        }
    }

    public void kill() {
        isAlive = false;
        this.interrupt();
    }
}