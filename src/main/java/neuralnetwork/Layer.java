package neuralnetwork;

import java.util.ArrayList;
import java.util.List;

class Layer {
    private final List<Perceptron> perceptron;
    private final int perceptronNum;
    private final int inputsNum;

    Layer(int inputsNum, int perceptronNum) {
        this.perceptronNum = perceptronNum;
        this.perceptron = new ArrayList<>();
        this.inputsNum = inputsNum;

        for (int i = 0; i < perceptronNum; i++) {
            Perceptron perceptron = new Perceptron(inputsNum);
            this.perceptron.add(perceptron);
        }
    }

    Layer(Layer layer1, Layer layer2) {
        this.perceptronNum = layer1.perceptronNum;
        this.perceptron = new ArrayList<>();
        this.inputsNum = layer1.inputsNum;

        for (int i = 0; i < perceptronNum; ++i) {
            Perceptron perceptron;
            if (Math.random() > 0.5) {
                perceptron = new Perceptron(layer1.getPerceptron(i));
            } else {
                perceptron = new Perceptron(layer2.getPerceptron(i));
            }

            this.perceptron.add(perceptron);
        }

    }

    Layer(Layer layer1) {
        this.perceptronNum = layer1.perceptronNum;
        this.perceptron = new ArrayList<Perceptron>();
        this.inputsNum = layer1.inputsNum;

        for (int i = 0; i < perceptronNum; i++) {
            Perceptron perceptron = new Perceptron(layer1.getPerceptron(i));
            this.perceptron.add(perceptron);
        }
    }

    public String toString() {
        return "(in: " + this.inputsNum + "; perc: " + this.perceptronNum + ")";
    }

    double[] run(double[] input) {
        double[] output = new double[perceptronNum];

        for (int i = 0; i < perceptronNum; i++) {
            output[i] = perceptron.get(i).calculate(input);
        }

        return output;
    }

    double[] runNoActivationFunc(double[] input) {
        double[] output = new double[perceptronNum];

        for (int i = 0; i < perceptronNum; i++) {
            output[i] = perceptron.get(i).calculateNoActFun(input);
        }

        return output;
    }

    Perceptron getPerceptron(int i) {
        return perceptron.get(i);
    }

    int size() {
        return perceptron.size();
    }

    void show() {
        for (int i = 0; i < perceptronNum; i++) {
            System.out.print("PERC " + i + ": ");
            System.out.println(perceptron.get(i).toString());
        }

    }

    String showString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < perceptronNum; i++) {
            result.append("\nPERC ").append(i).append(": \n");
            result.append(perceptron.get(i).toString());
        }

        return result.toString();

    }

    void mutation(double amount, double percent) {
        for (int i = 0; i < perceptronNum; i++) {
            perceptron.get(i).mutate(amount, percent);
        }

    }
}
