package NeuralNetwork;

import java.util.Arrays;

public class BlackBox {

    //private NeuralNetwork neuralNetwork = new NeuralNetwork(13, 4, 3, 30, true);
    //private NeuralNetwork neuralNetwork = new NeuralNetwork(10, 4, 1, 10, true);
    Genome genome;

    public BlackBox() {
    }

    public BlackBox(Genome genome) {
        this.genome = genome;
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
        double convField1p = convCharToDbl(field1, 'P');
        double convField1f = convCharToDbl(field1, 'F');
        double convField1x = convCharToDbl(field1, 'X');

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
        double convField4p = convCharToDbl(field4, 'P');
        double convField4f = convCharToDbl(field4, 'F');
        double convField4x = convCharToDbl(field4, 'X');

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
        double convField2p = convCharToDbl(field2, 'P');
        double convField2f = convCharToDbl(field2, 'F');
        double convField2x = convCharToDbl(field2, 'X');

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
        double convField3p = convCharToDbl(field3, 'P');
        double convField3f = convCharToDbl(field3, 'F');
        double convField3x = convCharToDbl(field3, 'X');

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
        double convField5p = convCharToDbl(field5, 'P');
        double convField5f = convCharToDbl(field5, 'F');
        double convField5x = convCharToDbl(field5, 'X');

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
        double convField6p = convCharToDbl(field6, 'P');
        double convField6f = convCharToDbl(field6, 'F');
        double convField6x = convCharToDbl(field6, 'X');

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
        double convField7p = convCharToDbl(field7, 'P');
        double convField7f = convCharToDbl(field7, 'F');
        double convField7x = convCharToDbl(field7, 'X');

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
        double convField8p = convCharToDbl(field8, 'P');
        double convField8f = convCharToDbl(field8, 'F');
        double convField8x = convCharToDbl(field8, 'X');

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
        double convField9p = convCharToDbl(field9, 'P');
        double convField9f = convCharToDbl(field9, 'F');
        double convField9x = convCharToDbl(field9, 'X');

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
        double convField10p = convCharToDbl(field10, 'P');
        double convField10f = convCharToDbl(field10, 'F');
        double convField10x = convCharToDbl(field10, 'X');

        //inputs should be between 0.0 and 0.99 --> radians/Math.PI & energy/maxEnergy
        //add simpleBias
        double simpleBias = 1.0;
        double[] input = {convField1p, convField2p, convField3p, convField4p, convField5p, convField6p, convField9p, convField10p,
                convField1x, convField2x, convField3x, convField4x, convField5x, convField6x, convField9x, convField10x,
                convField1f, convField2f, convField3f, convField4f, convField5f, convField6f, convField9f, convField10f,
                energy / 150, simpleBias};

        //double[] output = neuralNetwork.generate(input);
        double[] output = this.getGenome().getNeuralNetwork().run(input);

        //interpretation:
        boolean[] outputBool = new boolean[4];

        for (int i = 0; i < 4; i++) {
            outputBool[i] = output[i] == 1;
        }
        return new OutputMove(outputBool[0], outputBool[1], outputBool[2], outputBool[3]);
    }

    private double convCharToDbl(char ch, char strike) {

        double converted = 0.0;
        if (ch == strike) {
            converted = 1.0;
        }

        return converted;
    }

//    private double convCharToDbl(char ch) {
//
//        //double converted = 0.5;
//        double converted = 0.0; // nothing == '0'
//        if (ch == 'F') { // feed
//            //coverted = 0.99;
//            converted = -2.0;
//        }
//        if (ch == 'M') { // hide place
//            //converted = 0.75;
//            converted = 0.0;
//        }
//        if (ch == 'X') { // another prey
//            //converted = 0.55;
//            //converted = 0.1;
//            converted = 0.0;
//        }
//        if (ch == 'P') { //predator
//            converted = 2.0;
//        }
//        return converted;
//    }

    public String showGenome() {
        //return Arrays.deepToString(neuralNetwork.getGenome().getWeights()) + "\n" + Arrays.deepToString(neuralNetwork.getGenome().getWeights());
        return "mock";
    }

    public Genome getGenome() {
        return this.genome;
    }
}
