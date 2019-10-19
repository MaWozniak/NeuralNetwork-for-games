package Main;

public class Simulation {

    Simulation(int guiFramerate, int biomFramerate, int organismFramerate, boolean fullspeed, int preyNum, int predatorsNum, boolean modelFrame) throws InterruptedException {

        //here I must add 'model-view-controller' pattern: Gui + main.Biom(with Model) transfer to some Controller
        //at the moment I transfer main.Biom to Gui

        ModelView model = new ModelView(modelFrame);
        Biom biom = new Biom(preyNum, predatorsNum, biomFramerate, fullspeed, model, organismFramerate);
        GUI gui = new GUI(guiFramerate, biom);


    }
}
