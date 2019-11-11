package NeuralNetwork;

import java.util.Arrays;

public class BlackBox {

    private NeuralNetwork neuralNetwork = new NeuralNetwork(14, 2, 3, 30, true);

    public BlackBox() {
    }

    public BlackBox(Genome genome) {
        this.neuralNetwork.injectGenome(genome);
    }

    public OutputMove move(char[][] model, double xPos, double yPos, double radians, double velocity, double angle, double energy) {

        //now there is 3 point of view -> will be 11
        int x = (int) xPos - 5 + (int) (55.0 * Math.cos(radians));
        int y = (int) yPos - 5 + (int) (55.0 * Math.sin(radians));
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

        x = (int) xPos - 5 + (int) (6.0 * Math.cos(radians));
        y = (int) yPos - 5 + (int) (6.0 * Math.sin(radians));
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

        char field4 = model[x][y];
        double convField4 = convCharToDbl(field4);

        x = ((int) xPos - 5) + (int) (40.0 * Math.cos(radians) - (int) (50.0 * Math.sin(radians)));
        y = ((int) yPos - 5) + (int) (40.0 * Math.sin(radians) + (int) (50.0 * Math.cos(radians)));
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

        x = ((int) xPos - 5) + (int) (40.0 * Math.cos(radians) + (int) (50.0 * Math.sin(radians)));
        y = ((int) yPos - 5) + (int) (40.0 * Math.sin(radians) - (int) (50.0 * Math.cos(radians)));
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

        x = ((int) xPos - 5) - (int) (14.0 * Math.cos(radians) + (int) (14.0 * Math.sin(radians)));
        y = ((int) yPos - 5) - (int) (14.0 * Math.sin(radians) - (int) (14.0 * Math.cos(radians)));
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
        char field5 = model[x][y];
        double convField5 = convCharToDbl(field5);

        x = ((int) xPos - 5) - (int) (14.0 * Math.cos(radians) - (int) (14.0 * Math.sin(radians)));
        y = ((int) yPos - 5) - (int) (14.0 * Math.sin(radians) + (int) (14.0 * Math.cos(radians)));
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
        char field6 = model[x][y];
        double convField6 = convCharToDbl(field6);

        x = ((int) xPos - 5) + (int) (25.0 * Math.cos(radians) + (int) (20.0 * Math.sin(radians)));
        y = ((int) yPos - 5) + (int) (25.0 * Math.sin(radians) - (int) (20.0 * Math.cos(radians)));
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
        char field7 = model[x][y];
        double convField7 = convCharToDbl(field7);

        x = ((int) xPos - 5) + (int) (25.0 * Math.cos(radians) - (int) (20.0 * Math.sin(radians)));
        y = ((int) yPos - 5) + (int) (25.0 * Math.sin(radians) + (int) (20.0 * Math.cos(radians)));
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
        char field8 = model[x][y];
        double convField8 = convCharToDbl(field8);

        x = ((int) xPos - 5) + (int) (10.0 * Math.cos(radians) + (int) (40.0 * Math.sin(radians)));
        y = ((int) yPos - 5) + (int) (10.0 * Math.sin(radians) - (int) (40.0 * Math.cos(radians)));
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
        char field9 = model[x][y];
        double convField9 = convCharToDbl(field9);

        x = ((int) xPos - 5) + (int) (10.0 * Math.cos(radians) - (int) (40.0 * Math.sin(radians)));
        y = ((int) yPos - 5) + (int) (10.0 * Math.sin(radians) + (int) (40.0 * Math.cos(radians)));
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
        char field10 = model[x][y];
        double convField10 = convCharToDbl(field10);

        //inputs should be between 0.0 and 0.99 --> radians/Math.PI & energy/maxEnergy
        double[] input = {convField1, convField2, convField3, convField4, convField5, convField6, convField7,
                convField8, convField9, convField10, radians, velocity, angle, energy};

        double[] output = neuralNetwork.generate(input);

        output[0] = 10 * ((1 / (1 + Math.exp(output[0]))) - 0.5);
        output[1] = (1 / (1 + Math.exp(output[1]))) - 0.05;

        //return new OutputMove((int) (10 * (output[0] - 0.5)), output[1] - 0.2);
        //return new OutputMove((int) (0.04 * output[0] - 0.02), 0.8 * (output[1] / 5) - 0.05); // was: 0.8*(output[1] / 5) - 0.1
        return new OutputMove((int) output[0], output[1]); // was: 0.8*(output[1] / 5) - 0.1
    }

    private double convCharToDbl(char ch) {
        //double converted = 0.5; // nothing == '0'
        double converted = 0.2; // nothing == '0'
        if (ch == 'F') { // feed
            //coverted = 0.99;
            converted = 3.99;
        }
        if (ch == 'M') { // hide place
            //converted = 0.75;
            converted = 0.15;
        }
        if (ch == 'X') { // another prey
            //converted = 0.55;
            converted = 0.25;
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
