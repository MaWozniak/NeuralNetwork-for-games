package NeuralNetwork;

import java.util.Arrays;

public class BlackBox {

    double[] randomWeights1 = {Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5};
    double[] randomBiases1 = {Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5};

    double[] randomWeights2 = {Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5};
    double[] randomBiases2 = {Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5};

    public BlackBox() {
        //LOG:
        System.out.println(this.hashCode());
        System.out.println(Arrays.toString(randomWeights1));
        System.out.println(Arrays.toString(randomBiases1));
        System.out.println(Arrays.toString(randomWeights2));
        System.out.println(Arrays.toString(randomBiases2));
        System.out.println();
    }

    public OutputMove move(char[][] model, double xPos, double yPos, double radians, double energy) {

        /*
        first: making a neural network layers on harcoded values
        input 13 > 3 layers x 30 neurons > 2 values on output
        use weights annd bias
        */

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
        double[] input = {convField1, convField2, convField3, radians / Math.PI, energy / 150};

        //layer of 4 neurons
        double[] layer1 = new double[4];

//        double[] randomWeights1 = {Math.random() -0.5, Math.random() -0.5, Math.random() -0.5, Math.random() -0.5, Math.random() -0.5};
//        double[] randomBiases1 = {Math.random() -0.5, Math.random() -0.5, Math.random() -0.5, Math.random() -0.5, Math.random() -0.5};

        layer1[0] = input[0] * randomWeights1[0] + randomBiases1[0] + input[1] * randomWeights1[1] + randomBiases1[1]
                + input[2] * randomWeights1[2] + randomBiases1[2] + input[3] * randomWeights1[3] + randomBiases1[3];

//        double[] randomWeights2 = {Math.random() -0.5, Math.random() -0.5, Math.random() -0.5, Math.random() -0.5, Math.random() -0.5};
//        double[] randomBiases2 = {Math.random() -0.5, Math.random() -0.5, Math.random() -0.5, Math.random() -0.5, Math.random() -0.5};

        layer1[1] = input[0] * randomWeights2[0] + randomBiases2[0] + input[1] * randomWeights2[1] + randomBiases2[1]
                + input[2] * randomWeights2[2] + randomBiases2[2] + input[3] * randomWeights2[3] + randomBiases2[3];

        //etc layer[2] & layer[3] --> layer1 -> layer2 --> layer2 -> output(2 nodes: angle & acceleration)
        // GENERICS MECHANISM !!!

        return new OutputMove((int) (10 * layer1[0]), layer1[1]);
        //return new OutputMove(2, 0.1);


    }

    public OutputMove mocapMove(char[][] model, double xPos, double yPos, double radians, double energy) {
        return new OutputMove(2, 0.1);
    }

    double convCharToDbl(char ch) {
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
}
