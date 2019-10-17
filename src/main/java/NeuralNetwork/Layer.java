package NeuralNetwork;

import java.util.List;

public class Layer {

    private List<Double> inputs;
    private List<Double> outputs;
    private List<Double> weights;
    private List<Double> bias;

    List<Double> process(List<Double> inputs) {
        for (Double output : outputs) {

            for (Double input : inputs) {

                //output = input*weights + bias;

            }

        }
        return outputs;
    }

}
