package Game;

import java.io.IOException;

public class TestMain {

    public static void main(String[] args) throws InterruptedException, IOException {

        //30FPS~ 33 - 60FPS~ 17 - 300FPS~ 3 - good for fast simulation
        Simulation test = new Simulation(30, 30, 10, false, 0, 50, 1, false);

    }
}
