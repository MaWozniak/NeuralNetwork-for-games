import GUI.GUI;
import Game.Game;
import Game.Stage.StageManager;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        //strategy - array of generation number when starts next stage
        int factor = 25;
        int[] strategy = {1 * factor, 6 * factor, 10 * factor, 14 * factor, 18 * factor};
        StageManager stageManager = new StageManager(strategy);

        Game game = new Game(stageManager);

        new GUI(game);
    }
}
