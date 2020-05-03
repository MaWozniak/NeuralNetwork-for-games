package Game;

import GUI.GUI;
import GUI.StartScreen;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {

        //30FPS~ 33 - 60FPS~ 17 - 300FPS~ 3 - good for fast simulation
        ModelView model = new ModelView(false);
        Biom biom = new Biom(110, 0, 3, 50, 30, false, model, 15,
                true, false, true, false, true, 350, 150, 85);
        new GUI(30, biom);

        //  new StartScreen();
    }
}

