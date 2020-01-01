package Game;

import GUI.GUI;

import java.io.IOException;

public class TestMain {

    public static void main(String[] args) throws InterruptedException, IOException {

        //30FPS~ 33 - 60FPS~ 17 - 300FPS~ 3 - good for fast simulation

        ModelView model = new ModelView(false);
        Biom biom = new Biom(0, 0, 50, 30, false, model, 15,
                true, true, true, false, true, 200.0);
        GUI gui = new GUI(30, biom);
    }
}
