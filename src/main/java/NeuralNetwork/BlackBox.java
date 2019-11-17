package NeuralNetwork;

import java.util.Arrays;

public class BlackBox {

    //private NeuralNetwork neuralNetwork = new NeuralNetwork(13, 4, 3, 30, true);
    private NeuralNetwork neuralNetwork = new NeuralNetwork(9, 4, 2, 9, true);

    public BlackBox() {
    }

    public BlackBox(Genome genome) {
        this.neuralNetwork.injectGenome(genome);
    }

    public OutputMove move(char[][] model, double xPos, double yPos, double angle, double speed, double energy) {

        int x = (int) xPos - 5 + (int) (55.0 * Math.sin(angle));
        int y = (int) yPos - 5 - (int) (55.0 * Math.cos(angle));
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

        x = (int) xPos - 5 + (int) (3.0 * Math.sin(angle));
        y = (int) yPos - 5 - (int) (3.0 * Math.cos(angle));
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

        x = ((int) xPos - 5) + (int) (50.0 * Math.cos(angle) + (int) (40.0 * Math.sin(angle)));
        y = ((int) yPos - 5) + (int) (50.0 * Math.sin(angle) - (int) (40.0 * Math.cos(angle)));
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

        x = ((int) xPos - 5) - (int) (50.0 * Math.cos(angle) - (int) (40.0 * Math.sin(angle)));
        y = ((int) yPos - 5) - (int) (50.0 * Math.sin(angle) - +(int) (40.0 * Math.cos(angle)));
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

        x = ((int) xPos - 5) + (int) (16.0 * Math.cos(angle) - (int) (16.0 * Math.sin(angle)));
        y = ((int) yPos - 5) + (int) (16.0 * Math.sin(angle) + (int) (16.0 * Math.cos(angle)));
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

        x = ((int) xPos - 5) - (int) (16.0 * Math.cos(angle) + (int) (16.0 * Math.sin(angle)));
        y = ((int) yPos - 5) - (int) (16.0 * Math.sin(angle) - (int) (16.0 * Math.cos(angle)));
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

        x = ((int) xPos - 5) + (int) (20.0 * Math.cos(angle) + (int) (25.0 * Math.sin(angle)));
        y = ((int) yPos - 5) + (int) (20.0 * Math.sin(angle) - (int) (25.0 * Math.cos(angle)));
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

        x = ((int) xPos - 5) - (int) (20.0 * Math.cos(angle) - (int) (25.0 * Math.sin(angle)));
        y = ((int) yPos - 5) - (int) (20.0 * Math.sin(angle) + (int) (25.0 * Math.cos(angle)));
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

        x = ((int) xPos - 5) + (int) (40.0 * Math.cos(angle) + (int) (10.0 * Math.sin(angle)));
        y = ((int) yPos - 5) + (int) (40.0 * Math.sin(angle) - (int) (10.0 * Math.cos(angle)));
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

        x = ((int) xPos - 5) - (int) (40.0 * Math.cos(angle) - (int) (10.0 * Math.sin(angle)));
        y = ((int) yPos - 5) - (int) (40.0 * Math.sin(angle) + (int) (10.0 * Math.cos(angle)));
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
        double[] input = {convField1, convField2, convField3, convField4, convField5, convField6, convField9, convField10, energy / 150};

        double[] output = neuralNetwork.generate(input);

        //interpretation:
        boolean[] outputBool = new boolean[4];

        for (int i = 0; i < 4; i++) {
            outputBool[i] = output[i] == 1;
        }
        return new OutputMove(outputBool[0], outputBool[1], outputBool[2], outputBool[3]);
    }

    private double convCharToDbl(char ch) {

        //double converted = 0.5;
        double converted = 0.0; // nothing == '0'
        if (ch == 'F') { // feed
            //coverted = 0.99;
            converted = 2.0;
        }
        if (ch == 'M') { // hide place
            //converted = 0.75;
            converted = 0.0;
        }
        if (ch == 'X') { // another prey
            //converted = 0.55;
            converted = -0.1;
        }
        if (ch == 'P') { //predator
            converted = -2.0;
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
