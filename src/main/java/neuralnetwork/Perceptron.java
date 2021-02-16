package neuralnetwork;

import java.text.DecimalFormat;

public class Perceptron {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private int inputsNum;
    private double[] weight;
    private double bias;
    private ActivationFunctions activationFunc;

    Perceptron(int inputsNum) {
        this.inputsNum = inputsNum;
        this.activationFunc = new ActivationFunctions();
        weight = new double[inputsNum];
    }

    Perceptron(Perceptron perceptron1, Perceptron perceptron2) {
        this.inputsNum = perceptron1.inputsNum;
        this.activationFunc = perceptron1.activationFunc;
        weight = new double[perceptron1.inputsNum];

        for (int i = 0; i < weight.length; i++) {
            if (Math.random() > 0.5) {
                weight[i] = perceptron1.weight[i];
            } else {
                weight[i] = perceptron2.weight[i];
            }
        }

    }

    Perceptron(Perceptron perceptron1) {
        this.inputsNum = perceptron1.inputsNum;
        this.activationFunc = perceptron1.activationFunc;
        weight = new double[perceptron1.inputsNum];
        this.bias = perceptron1.getBias();

        for (int i = 0; i < weight.length; i++) {
            weight[i] = perceptron1.weight[i];
        }

    }

    double calculate(double[] inputs) {
        double output = 0.0;

        for (int i = 0; i < inputsNum; i++) {
            output += inputs[i] * weight[i];
        }

        output += bias;
        return activationFunc.run("STEP", output);
    }

    double calculateNoActFun(double[] inputs) {
        double output = 0.0;

        for (int i = 0; i < inputsNum; i++) {
            output += inputs[i] * weight[i];
        }

        output += bias;
        return output;
    }

    public double[] getWeight() {
        return weight;
    }

    public void setWeight(double[] weight) {
        this.weight = weight;
    }

    void setWeight(int i, double value) {
        this.weight[i] = value;
    }

    public double getBias() {
        return bias;
    }

    void setBias(double bias) {
        this.bias = bias;
    }

    int getWeightsNum() {
        return inputsNum;
    }

    public String toString() {
        StringBuilder string = new StringBuilder("W:");

        for (double v : weight) {
            string.append("[").append(df2.format(v)).append("]");
        }

        string.append(" +B:[").append(df2.format(this.bias)).append("]");
        return string.toString();
    }

    void mutate(double amount, double percent) {
        for (int i = 0; i < weight.length; i++) {
            if (Math.random() < amount) {
                double change = percent;
                if (Math.random() < 0.5) {
                    change = -percent;
                }

                if (Math.abs(weight[i] + change) > 1) {
                    weight[i] -= change;
                } else {
                    weight[i] += change;
                }
            }
        }

    }
}
