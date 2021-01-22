import GUI.GUI;
import Game.Game;
import Game.Stage.StageManager;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        int[] strategy = {2, 3, 4, 5, 6};
        StageManager stageManager = new StageManager(strategy);

        Game game = new Game(stageManager);

        new GUI(game);
    }
}
