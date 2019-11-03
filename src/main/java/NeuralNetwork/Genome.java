package NeuralNetwork;

import java.util.Arrays;

class Genome {

    private double[][][] weights;
    private double[][] biases;

    Genome(int numLayers, int numNeurons, int inputs, int outputs, boolean bias) {

        weights = new double[numLayers + 2][numNeurons][numNeurons];
        generateWeights(numLayers, numNeurons, inputs, outputs);

        biases = new double[numLayers + 2][numNeurons];
        if (bias) {
            generateBiases(numLayers, numNeurons, inputs, outputs);
        }
    }

    double[][][] getWeights() {
        return weights;
    }

    double[][] getBiases() {
        return biases;
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

        for (int i = 0; i < inputs; i++) {
            biases[0][i] = Math.random() - 0.5;
        }
        for (int i = 1; i < numLayers + 1; i++) {
            for (int j = 0; j < numNeurons; j++) {
                biases[i][j] = Math.random() - 0.5;
            }
        }
        for (int i = 0; i < outputs; i++) {
            biases[numLayers + 1][i] = Math.random() - 0.5;
        }
    }

    void printOut() {
        System.out.println(Arrays.deepToString(this.weights));
        System.out.println(Arrays.deepToString(this.biases));
    }
}
