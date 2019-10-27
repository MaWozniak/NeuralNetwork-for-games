package Main;

import NeuralNetwork.BlackBox;
import NeuralNetwork.OutputMove;

class AiCPUprey {

    private BlackBox blackBox = new BlackBox();

    AiCPUprey() {
    }

    void thinking(char[][] model, PreyAI preyAI) {

        OutputMove move = blackBox.move(model);

        int updateDirection = move.getAngle();
        double acceleration = move.getAccelleration();

        preyAI.directionAngle += updateDirection;
        preyAI.velocity += acceleration;
        preyAI.radians = (Math.PI / 180) * (preyAI.directionAngle);

    }
}
