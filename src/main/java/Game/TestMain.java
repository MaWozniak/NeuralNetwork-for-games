package Game;

import GUI.GUI;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {

        //30FPS~ 33 - 60FPS~ 17 - 300FPS~ 3 - good for fast simulation
        ModelView model = new ModelView(false);
        Biom biom = new Biom(0, 2, 50, 30, false, model, 15,
                true, false, true, false, true, 400, 250, 100);
        new GUI(30, biom);
    }
}
