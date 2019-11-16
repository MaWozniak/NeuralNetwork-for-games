package NeuralNetwork;

import java.util.Arrays;

public class Genome {

    private double[][][] weights;
    private double[][] biases;
    private double score;
    private String id = "";

    public Genome() {
    }

    public Genome(int numLayers, int numNeurons, int inputs, int outputs, boolean bias) {

        weights = new double[numLayers + 2][numNeurons][numNeurons];
        generateWeights(numLayers, numNeurons, inputs, outputs);

        biases = new double[numLayers + 2][numNeurons];
        if (bias) {
            generateBiases(numLayers, numNeurons, inputs, outputs);
        }
    }

    public double[][][] getWeights() {
        return weights;
    }

    public void setWeights(double[][][] weights) {
        this.weights = weights;
    }

    public double[][] getBiases() {
        return biases;
    }

    public void setBiases(double[][] biases) {
        this.biases = biases;
    }

    public void setWeight(int i, int j, int k, double newValue) {
        this.weights[i][j][k] = newValue;
    }

    public void setBias(int i, int j, double newValue) {
        this.biases[i][j] = newValue;
    }

    public void generateWeights(int numLayers, int numNeurons, int inputs, int outputs) {

        for (int n = 0; n < numNeurons; n++) {

            for (int i = 0; i < inputs; i++) {
                weights[0][i][n] = 2 * Math.random() - 1;
            }
            for (int i = 1; i < numLayers + 1; i++) {
                for (int j = 0; j < numNeurons; j++) {
                    weights[i][j][n] = 2 * Math.random() - 1;
                }
            }
            for (int i = 0; i < outputs; i++) {
                weights[numLayers + 1][i][n] = 2 * Math.random() - 1;
            }
        }

    }

    public void generateBiases(int numLayers, int numNeurons, int inputs, int outputs) {

        for (int i = 0; i < inputs; i++) {
            //biases[0][i] = 2 * Math.random() - 1;
            biases[0][i] = 1;
        }
        for (int i = 1; i < numLayers + 1; i++) {
            for (int j = 0; j < numNeurons; j++) {
                //biases[i][j] = 2 * Math.random() - 1;
                biases[i][j] = 1;
            }
        }
        for (int i = 0; i < outputs; i++) {
            //biases[numLayers + 1][i] = 2 * Math.random() - 1;
            biases[numLayers + 1][i] = 1;
        }
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id + "-" + this.id;
    }

    public void appendId(String info) {
        this.id += info;
    }

    void printOut() {
        System.out.println(Arrays.deepToString(this.weights));
        System.out.println(Arrays.deepToString(this.biases));
    }

    public String saveToMemory() {
        return this.toString() + "\n" + "weights: \n" + Arrays.deepToString(this.getWeights())
                + "\nbiases: \n" + Arrays.deepToString(this.getBiases()) + "\n\n- - - - - - - - - - - - -\n";
    }
}
