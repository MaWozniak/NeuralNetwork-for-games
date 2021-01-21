import GUI.GUI;
import Game.Game;
import Game.Configuration;
import Game.Stage.StageManager;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        Configuration configuration = new Configuration(100, 50);

        int[] strategy = {2, 3, 4, 5, 6};
        StageManager stageManager = new StageManager(strategy);

        Game game = new Game(configuration, stageManager);

        new GUI(30, game);

    }
}
