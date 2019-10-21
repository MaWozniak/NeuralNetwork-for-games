package Main;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {

        //30FPS~ 33 - 60FPS~ 17 - 300FPS~ 3 - good for fast simulation

        //main.Simulation test = new main.Simulation(60, 30, 6, false, 20, 0, 3, false);
        //main.Simulation test = new main.Simulation(30, 1000, 6, true, 200, 0, 10, false);
        Simulation test = new Simulation(30, 30, 6, false, 50, 10, 5, false);

        /*
        ADD Some Button (on frame) to change biom framerate for e. 10 - 30 - 100 -1000  etc
         */

    }
}
