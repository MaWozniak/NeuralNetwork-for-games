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

        OutputMove move = blackBox.move(model, preyAI.getX(), preyAI.getY(), preyAI.getRadians(), preyAI.getVelocity(), preyAI.getDirectionAngle(), preyAI.getEnergy());

        int updateDirection = move.getAngle();
        double acceleration = move.getAccelleration();

        preyAI.directionAngle += updateDirection;
        preyAI.velocity += acceleration;
        preyAI.radians = (Math.PI / 180) * (preyAI.directionAngle);

    }

    String showGenome() {
        return blackBox.showGenome();
    }

    Genome getGenome() {
        return blackBox.getGenome();
    }
}
