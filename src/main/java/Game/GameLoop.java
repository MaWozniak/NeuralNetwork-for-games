package Game;

import java.io.IOException;

public class GameLoop implements Runnable {

    private static final boolean FULL_SPEED = false;
    private static final int FPS = 30;

    private final Game game;
    private int millis;

    GameLoop(Game game) {
        this.game = game;
        this.millis = 1000 / FPS;
    }

    @Override
    public void run() {
        while (true) {
            try {
                game.gameLoop();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!FULL_SPEED) {
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