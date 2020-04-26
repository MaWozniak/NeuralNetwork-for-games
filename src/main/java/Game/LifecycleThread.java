package Game;

import java.io.IOException;

public class LifecycleThread implements Runnable {

    private Biom biom;
    private int millis;
    private boolean fullspeed;

    LifecycleThread(int framerate, boolean fullspeed, Biom biom) {
        this.biom = biom;
        this.millis = 1000 / framerate;
        this.fullspeed = fullspeed;
    }

    @Override
    public void run() {
        while (true) {
            try {
                biom.lifecycle();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!fullspeed) {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    void setMillis(int millis) {
        this.millis = millis;
    }

    int getFramerate() {
        return 1000 / millis;
    }
}