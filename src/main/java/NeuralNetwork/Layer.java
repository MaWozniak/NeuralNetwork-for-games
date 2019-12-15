package NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

class Layer {

    private List<Perceptron> perceptrons;
    private int perceptronsNum;
    private int inputsNum;

    Layer(int inputsNum, int perceptronsNum) {
        this.perceptronsNum = perceptronsNum;
        this.perceptrons = new ArrayList<Perceptron>();
        this.inputsNum = inputsNum;

        for (int i = 0; i < perceptronsNum; i++) {
            Perceptron perceptron = new Perceptron(inputsNum);
            perceptrons.add(perceptron);
        }
    }

    Layer(Layer layer1, Layer layer2) {
        this.perceptronsNum = layer1.perceptronsNum;
        this.perceptrons = new ArrayList<Perceptron>();
        this.inputsNum = layer1.inputsNum;

        for (int i = 0; i < perceptronsNum; i++) {
            Perceptron perceptron = new Perceptron(layer1.getPerceptron(i), layer2.getPerceptron(i));
            perceptrons.add(perceptron);
        }
    }

    Layer(Layer layer1) {
        this.perceptronsNum = layer1.perceptronsNum;
        this.perceptrons = new ArrayList<Perceptron>();
        this.inputsNum = layer1.inputsNum;

        for (int i = 0; i < perceptronsNum; i++) {
            Perceptron perceptron = new Perceptron(layer1.getPerceptron(i));
            perceptrons.add(perceptron);
        }
    }

    double[] run(double[] input) {
        double[] output = new double[perceptronsNum];

        for (int i = 0; i < perceptronsNum; i++) {
            output[i] = perceptrons.get(i).calculate(input);
        }

        return output;
    }

    Perceptron getPerceptron(int i) {
        return perceptrons.get(i);
    }

    int size() {
        return perceptrons.size();
    }

    public String toString() {
        return "(in: " + this.inputsNum + "; perc: " + this.perceptronsNum + ")";
    }

    void show() {

        for (int i = 0; i < perceptronsNum; i++) {
            System.out.print("PERC " + i + ": ");
            System.out.println(perceptrons.get(i).toString());
        }
    }

    void mutation(double amount, double percent) {
        for (int i = 0; i < perceptronsNum; i++) {
            perceptrons.get(i).mutate(amount, percent);
        }
    }
}
