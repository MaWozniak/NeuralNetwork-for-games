package game.organisms;

import neuralnetwork.BlackBox;
import neuralnetwork.Genome;
import neuralnetwork.OutputMove;

class PreyLogic {
    private BlackBox blackBox;
    private boolean forceMove;

    PreyLogic(Genome genome, boolean forceMove) {
        blackBox = new BlackBox(genome);
        this.forceMove = forceMove;
    }

    void thinking(byte[][] model, Prey prey) {
        OutputMove move = this.blackBox.move(model, prey.getX(), prey.getY(), prey.getAngle(), prey.getSpeed(), prey.getEnergy(), prey.getStamina(), prey.getAge());
        if (forceMove) {
            prey.rightFrontTail = true;
            prey.leftFrontTail = false;
        } else {
            prey.rightFrontTail = move.isKey1();
            prey.leftFrontTail = move.isKey2();
        }

        prey.leftBackTail = move.isKey4();
        prey.rightBackTail = move.isKey3();

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
