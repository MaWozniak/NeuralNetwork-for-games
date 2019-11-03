package Game;

import NeuralNetwork.BlackBox;
import NeuralNetwork.OutputMove;

class PreyLogicAI {

    private BlackBox blackBox = new BlackBox();

    PreyLogicAI() {
    }

    void thinking(char[][] model, PreyAI preyAI) {

        OutputMove move = blackBox.move(model, preyAI.getX(), preyAI.getY(), preyAI.getRadians(), preyAI.getEnergy());

        int updateDirection = move.getAngle();
        double acceleration = move.getAccelleration();

        preyAI.directionAngle += updateDirection;
        preyAI.velocity += acceleration;
        preyAI.radians = (Math.PI / 180) * (preyAI.directionAngle);

    }

    String showGenome() {
        return blackBox.showGenome();
    }
}
