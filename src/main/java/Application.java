import game.stage.*;
import gui.GUI;
import game.Game;


public class Application {

    public static void main(String[] args) throws InterruptedException {

        StageManager stageManager = new FixedStageManager();

        Game game = new Game(stageManager);

        new GUI(game);
    }
}
