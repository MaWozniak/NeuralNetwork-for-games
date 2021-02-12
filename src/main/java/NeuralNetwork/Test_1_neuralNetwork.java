package NeuralNetwork;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Test_1_neuralNetwork {

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 1, 3, 2);
        neuralNetwork.fillRandomWeights();
        neuralNetwork.setBias(0.0);

        double[] input = {1.0, 0.0};

        double[] output = neuralNetwork.run(input);

        //log:
        System.out.println("\ninput " + Arrays.toString(input) + "\n");
        for (int i = 0; i < neuralNetwork.getLayersNum(); i++) {
            System.out.println("layer [" + i + "] " + neuralNetwork.getLayer(i).toString());
            for (int j = 0; j < neuralNetwork.getLayer(i).size(); j++) {

                System.out.println("perceptron [" + j + "]");
                System.out.print(neuralNetwork.getLayer(i).getPerceptron(j).toString());
                System.out.println(" => ["
                        + df2.format(neuralNetwork.getLayer(i).getPerceptron(j).calculateNoActFun(input)) + "] => AF["
                        + df2.format(neuralNetwork.getLayer(i).getPerceptron(j).calculate(input)) + "]");
            }
            System.out.println("=>");
            System.out.print(Arrays.toString(neuralNetwork.getLayer(i).run(input)) + "\n\n");
            input = neuralNetwork.getLayer(i).run(input);
        }
        System.out.println("output " + Arrays.toString(output));
        //end log

    }

}