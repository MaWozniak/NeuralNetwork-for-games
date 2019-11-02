package NeuralNetwork;

import java.util.Arrays;

class NeuralNetwork {

    private double[] inputs;
    private double[] outputs;
    private double[][][] weights;
    private double[][][] biases;
    private double[][] layers;
    private int numOfLayers;

    NeuralNetwork(int inputs, int outputs, int layers, int neurons, boolean bias) {

        this.inputs = new double[inputs];
        this.outputs = new double[outputs];
        this.layers = new double[layers][neurons];
        this.numOfLayers = layers;
        int allLayers = layers + 2; // => layers + input and output layers
        weights = new double[allLayers][neurons][neurons];
        generateWeights(layers, neurons, inputs, outputs);
        biases = new double[allLayers][neurons][neurons];
        if (bias) {
            generateBiases(layers, neurons, inputs, outputs);
        }
    }

    double[] generate(double[] inputs) {

        this.inputs = inputs;
        calculate(this.inputs, layers[0], weights[0], biases[0]);
        for (int i = 0; i < numOfLayers - 1; i++) {
            calculate(layers[i], layers[i + 1], weights[i + 1], biases[i + 1]);
        }
        calculate(layers[numOfLayers - 1], outputs, weights[numOfLayers - 1], biases[numOfLayers - 1]);
        return outputs;
    }

    private void calculate(double[] layer1, double[] layer2, double[][] weights, double[][] bias) {
        /*
        System.out.println("------LOG-- Start calculate");
        System.out.println("------LOG------------------ layer1= " + Arrays.toString(layer1));
        System.out.println("------LOG------------------ layer2= " + Arrays.toString(layer2));
         */
        for (int i = 0; i < layer2.length; i++) {
            for (int j = 0; j < layer1.length; j++) {
                layer2[i] += weights[j][i] * layer1[j] + bias[j][i];
            }

            // ACTIVATION FUNCTION: Sigmoid Function / Try also STEP FUNCTION & SINUS
            layer2[i] = 1 / (1 + Math.exp(layer2[i]));
            // STEP:
            //if(layer2[i] >= 0){layer2[i] = 1;} else {layer2[i] = 0;}
        }
        /*
        System.out.println("------LOG-- AFTER calculate layer1= " + Arrays.toString(layer1));
        System.out.println("------LOG-- AFTER calculate layer2= " + Arrays.toString(layer2));
        System.out.println("------LOG-- End calculate");
        System.out.println("-----------");
         */
    }

    void printOut() {
        System.out.println("in: " + Arrays.toString(this.inputs));
        System.out.println(Arrays.deepToString(this.weights));
        System.out.println(Arrays.deepToString(this.biases));
        System.out.println("ou " + Arrays.toString(this.outputs));
    }

    private void generateWeights(int numLayers, int numNeurons, int inputs, int outputs) {

        for (int n = 0; n < numNeurons; n++) {

            for (int i = 0; i < inputs; i++) {
                weights[0][i][n] = Math.random() - 0.5;
            }
            for (int i = 1; i < numLayers + 1; i++) {
                for (int j = 0; j < numNeurons; j++) {
                    weights[i][j][n] = Math.random() - 0.5;
                }
            }
            for (int i = 0; i < outputs; i++) {
                weights[numLayers + 1][i][n] = Math.random() - 0.5;
            }
        }

    }

    private void generateBiases(int numLayers, int numNeurons, int inputs, int outputs) {

        for (int n = 0; n < numNeurons; n++) {

            for (int i = 0; i < inputs; i++) {
                biases[0][i][n] = Math.random() - 0.5;
            }
            for (int i = 1; i < numLayers + 1; i++) {
                for (int j = 0; j < numNeurons; j++) {
                    biases[i][j][n] = Math.random() - 0.5;
                }
            }
            for (int i = 0; i < outputs; i++) {
                biases[numLayers + 1][i][n] = Math.random() - 0.5;
            }
        }
    }
}
