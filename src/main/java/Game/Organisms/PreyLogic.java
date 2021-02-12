package Game.Organisms;

import NeuralNetwork.BlackBox;
import NeuralNetwork.Genome;
import NeuralNetwork.OutputMove;

class PreyLogic {
    private BlackBox blackBox;
    private boolean forceMove;

    PreyLogic(Genome genome, boolean forceMove) {
        blackBox = new BlackBox(genome);
        this.forceMove = forceMove;
    }

    void thinking(char[][] model, Prey prey) {
        OutputMove move = this.blackBox.move(model, prey.getX(), prey.getY(), prey.getAngle(), prey.getSpeed(), prey.getEnergy(), prey.getStamina(), prey.getAge());
        if (forceMove) {
            prey.up = true;
            prey.down = false;
        } else {
            prey.up = move.isUp();
            prey.down = move.isDown();
        }

        prey.right = move.isRight();
        prey.left = move.isLeft();

    }

    Genome getGenome() {
        return blackBox.getGenome();
    }

    double[] getInputs() {
        return this.blackBox.getInput();
    }

    double[] getOutputs() {
        return this.blackBox.getOutput();
    }
}
