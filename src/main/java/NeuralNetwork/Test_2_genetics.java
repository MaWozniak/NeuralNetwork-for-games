package NeuralNetwork;

import java.text.DecimalFormat;

public class Test_2_genetics {

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(20, 20, 40, 1);
        neuralNetwork.fillRandomWeights();
        neuralNetwork.setBias(0.0);

        neuralNetwork.show();

        neuralNetwork.mutation(0.5, 0.1);

        neuralNetwork.show();

        NeuralNetwork neuralNetwork2 = new NeuralNetwork(20, 20, 40, 1);
        //neuralNetwork2.fillRandomWeights();
        neuralNetwork2.setAllBiases(77.00);
        neuralNetwork2.setBias(0.0);

        neuralNetwork2.show();

        NeuralNetwork neuralNetworkFromSelection = new NeuralNetwork(neuralNetwork, neuralNetwork2);

        neuralNetworkFromSelection.show();
    }

}