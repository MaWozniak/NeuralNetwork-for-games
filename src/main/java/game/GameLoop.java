package game;

import java.io.IOException;

public class GameLoop implements Runnable {

    private static final boolean FULL_SPEED = false;
    private int FPS = 1000; //30-60

    private final Game game;
    private final int millis;

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

    void setFrameRate(int frameRate) { this.FPS = frameRate; }

    int getFrameRate() { return this.FPS; }
}