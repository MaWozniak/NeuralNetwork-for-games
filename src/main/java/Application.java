import GUI.GUI;
import Game.Game;
import Game.ModelView;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        ModelView model = new ModelView(false);
        Game game = new Game(2, 50, model,
                true, false, true, false, true, 200, 230, 145);
        new GUI(30, game);
    }
}
