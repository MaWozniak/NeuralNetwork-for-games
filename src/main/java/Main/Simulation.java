package Main;

import GUI.GUI;

class Simulation {

    Simulation(int guiFramerate, int biomFramerate, int organismFramerate, boolean fullspeed, int preyNum, int AIpreyNum, int predatorsNum, boolean modelFrame) throws InterruptedException {

        ModelView model = new ModelView(modelFrame);
        Biom biom = new Biom(preyNum, predatorsNum, AIpreyNum, biomFramerate, fullspeed, model, organismFramerate);
        GUI gui = new GUI(guiFramerate, biom);


    }
}
