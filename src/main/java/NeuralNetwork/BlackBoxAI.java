package NeuralNetwork;

import Main.OutputMove;

public class BlackBoxAI {

    public BlackBoxAI() {
    }

    public OutputMove move(char[][] model) {
        return new OutputMove(1.0, 1.0);
    }
}
