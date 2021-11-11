import game.stage.*;
import gui.GUI;
import game.Game;


public class Application {

    public static void main(String[] args) throws InterruptedException {

        StageManager stageManager = new FixedStageManager();

        Game game = new Game(stageManager);

        new GUI(game);
    }

    /*

    przerabiac na kotlin

    sprawdzic czy mozna szybko puscic trzy symulacje na raz i na zmiane pokazywac "grę" w gui

    GenerationManager w Game->Generations ??

    czy mozna sledzic ich postep oddzielnie i czy mozna dorzucic dodatkowa wieksza mutacje

     */
}
