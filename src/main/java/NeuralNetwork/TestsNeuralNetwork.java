package NeuralNetwork;

import java.util.Arrays;

public class TestsNeuralNetwork {

    public static void main(String[] args) {

        //NeuralNetwork neuralNetwork = new NeuralNetwork(3,2,3,30,true);
        NeuralNetwork neuralNetwork = new NeuralNetwork(3, 2, 3, 30, true);
        neuralNetwork.printOut();
        double[] testInput1 = {0.89, 0.1, 0.08};
        System.out.println("Output = " + Arrays.toString(neuralNetwork.generate(testInput1)));
        double[] testInput2 = {0.0, 0.4432, 0.58};
        System.out.println("Output = " + Arrays.toString(neuralNetwork.generate(testInput2)));
        double[] testInput3 = {0.15, 1.0, 0.0054};
        System.out.println("Output = " + Arrays.toString(neuralNetwork.generate(testInput3)));
        double[] testInput4 = {0.444, 0.0, 0.68};
        System.out.println("Output = " + Arrays.toString(neuralNetwork.generate(testInput4)));

    }
}
