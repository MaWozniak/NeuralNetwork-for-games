public class Simulation {

    Simulation(int guiFramerate, int preyNum, int predatorsNum) throws InterruptedException {

        //here I must add 'model-view-controller' pattern: Gui + Biom(with Model) transfer to some Controller
        //at the moment I transfer Biom to Gui

        Biom biom = new Biom(preyNum, predatorsNum);
        GUI gui = new GUI(guiFramerate, biom);

    }
}
