import GUI.GUI;
import Game.Game;
import Game.Stage.StageManager;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        /*
            strategy - array of generation number ordering start of next stage
                      
        */

        int multiplier = 25;
        int[] strategy = {2 * multiplier, 6 * multiplier, 10 * multiplier, 14 * multiplier, 18 * multiplier};
        StageManager stageManager = new StageManager(strategy);

        Game game = new Game(stageManager);

        new GUI(game);
    }
}
