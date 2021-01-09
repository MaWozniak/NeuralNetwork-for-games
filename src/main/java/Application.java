import GUI.GUI;
import Game.Game;
import Game.ModelView;
import Game.Configuration;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        ModelView model = new ModelView(false);

        Configuration configuration = new Configuration(251, 4, 80);

        //Scenario clasa - scenario ma Faze(klasa) (np 3)
        //Faze ma FoodMenagara z rozna konfiguracja foodMin -max -position etc

        //nazwy Scheduler + Stage(Stages)
        //+klasa TestScenarios ze statycznym Scheduler z konkretnymi fazami :) tak jak TestBoards
        Game game = new Game(configuration, model, 230, 145);

        new GUI(30, game);
    }
}
