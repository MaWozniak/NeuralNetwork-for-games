package Game;

import NeuralNetwork.BlackBox;
import NeuralNetwork.Genome;
import NeuralNetwork.OutputMove;

class PreyLogicAI {

    private BlackBox blackBox;
    private boolean forceMove;

    PreyLogicAI(boolean forceMove) {
        blackBox = new BlackBox();
        this.forceMove = forceMove;
    }

    PreyLogicAI(Genome genome, boolean forceMove) {
        blackBox = new BlackBox(genome);
        this.forceMove = forceMove;
    }

    void thinking(char[][] model, PreyAI preyAI) {

        OutputMove move = blackBox.move(model, preyAI.getX(), preyAI.getY(), preyAI.getAngle(), preyAI.getSpeed(), preyAI.getEnergy());

        if (forceMove) {
            preyAI.up = true;
            preyAI.down = false;
        } else {
            preyAI.up = move.isUp();
            preyAI.down = move.isDown();
        }

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
