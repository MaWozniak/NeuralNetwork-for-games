package Game;

import NeuralNetwork.BlackBox;
import NeuralNetwork.Genome;
import NeuralNetwork.OutputMove;

class PreyLogicAI {

    private BlackBox blackBox;

    PreyLogicAI() {
        blackBox = new BlackBox();
    }

    PreyLogicAI(Genome genome) {
        blackBox = new BlackBox(genome);
    }

    void thinking(char[][] model, PreyAI preyAI) {

        OutputMove move = blackBox.move(model, preyAI.getX(), preyAI.getY(), preyAI.getAngle(), preyAI.getSpeed(), preyAI.getEnergy());

        preyAI.up = move.isUp();
        preyAI.down = move.isDown();
        preyAI.right = move.isRight();
        preyAI.left = move.isLeft();

    }

    String showGenome() {
        return blackBox.showGenome();
    }

    Genome getGenome() {
        return blackBox.getGenome();
    }
}
