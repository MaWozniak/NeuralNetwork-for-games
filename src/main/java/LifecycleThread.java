public class LifecycleThread implements Runnable {

    private Biom biom;
    private int millis;

    LifecycleThread(int framerate, Biom biom) {
        this.biom = biom;
        this.millis = 1000 / framerate;
    }

    @Override
    public void run() {
        while (true) {

            biom.lifecycle();

            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}