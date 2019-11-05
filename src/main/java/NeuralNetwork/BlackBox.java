package NeuralNetwork;

import java.util.Arrays;

public class BlackBox {

    private NeuralNetwork neuralNetwork = new NeuralNetwork(5, 2, 3, 30, true);

    public BlackBox() {
    }

    public BlackBox(Genome genome) {
        this.neuralNetwork.injectGenome(genome);
    }

    public OutputMove move(char[][] model, double xPos, double yPos, double radians, double energy) {

        //now there is 3 point of view -> will be 11
        int x = (int) xPos - 5 + (int) (90.0 * Math.cos(radians));
        int y = (int) yPos - 5 + (int) (90.0 * Math.sin(radians));
        if (x < 0) {
            x = 0;
        } // TO REFACTOR!
        if (x > 1250) {
            x = 1250;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > 850) {
            y = 850;
        }

        char field1 = model[x][y];
        double convField1 = convCharToDbl(field1);

        x = ((int) xPos - 5) + (int) (40.0 * Math.cos(radians) - (int) (40.0 * Math.sin(radians)));
        y = ((int) yPos - 5) + (int) (40.0 * Math.sin(radians) + (int) (40.0 * Math.cos(radians)));
        if (x < 0) {
            x = 0;
        } // TO REFACTOR!
        if (x > 1250) {
            x = 1250;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > 850) {
            y = 850;
        }
        char field2 = model[x][y];
        double convField2 = convCharToDbl(field2);

        x = ((int) xPos - 5) + (int) (40.0 * Math.cos(radians) + (int) (40.0 * Math.sin(radians)));
        y = ((int) yPos - 5) + (int) (40.0 * Math.sin(radians) - (int) (40.0 * Math.cos(radians)));
        if (x < 0) {
            x = 0;
        } // TO REFACTOR!
        if (x > 1250) {
            x = 1250;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > 850) {
            y = 850;
        }
        char field3 = model[x][y];
        double convField3 = convCharToDbl(field3);

        //inputs should be between 0.0 and 0.99 --> radians/Math.PI & energy/maxEnergy
        double[] input = {convField1, convField2, convField3, radians, energy};

        double[] output = neuralNetwork.generate(input);

        //return new OutputMove((int) (10 * (output[0] - 0.5)), output[1] - 0.2);
        return new OutputMove((int) (20 * output[0] - 10.0), 0.8 * (output[1] / 5) - 0.05); // was: 0.8*(output[1] / 5) - 0.1
    }

    private double convCharToDbl(char ch) {
        double converted = 0.5; // nothing == '0'
        if (ch == 'F') { // feed
            converted = 0.99;
        }
        if (ch == 'M') { // hide place
            converted = 0.75;
        }
        if (ch == 'X') { // another prey
            converted = 0.55;
        }
        if (ch == 'P') { //predator
            converted = 0.0;
        }
        return converted;
    }

    public String showGenome() {
        return Arrays.deepToString(neuralNetwork.getGenome().getWeights()) + "\n" + Arrays.deepToString(neuralNetwork.getGenome().getWeights());
    }

    public Genome getGenome() {
        return neuralNetwork.getGenome();
    }
}
