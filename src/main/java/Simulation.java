public class Simulation {

    Simulation(int guiFramerate, int biomFramerate, boolean fullspeed, int preyNum, int predatorsNum, boolean modelFrame) throws InterruptedException {

        //here I must add 'model-view-controller' pattern: Gui + Biom(with Model) transfer to some Controller
        //at the moment I transfer Biom to Gui

        ModelView model = new ModelView(modelFrame);
        Biom biom = new Biom(preyNum, predatorsNum, biomFramerate, fullspeed, model);
        GUI gui = new GUI(guiFramerate, biom);

    }
}
