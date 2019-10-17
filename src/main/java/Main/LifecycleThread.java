package Main;

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

            biom.lifecycle();

            if (!fullspeed) {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void setMillis(int millis) {
        this.millis = millis;
    }
}