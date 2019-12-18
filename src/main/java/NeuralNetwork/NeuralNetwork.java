package NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private List<Layer> layers;

    NeuralNetwork() {
    }

    public NeuralNetwork(int inputs, int hiddenLayers, int perceptrons, int outputs) {

        layers = new ArrayList<>();

        layers.add(new Layer(inputs, perceptrons));

        for (int i = 0; i < hiddenLayers - 1; i++) {
            layers.add(new Layer(perceptrons, perceptrons));
        }

        layers.add(new Layer(perceptrons, outputs));
    }

    NeuralNetwork(NeuralNetwork neuralNetwork1, NeuralNetwork neuralNetwork2) {

        layers = new ArrayList<>();

        for (int i = 0; i < neuralNetwork1.layers.size(); i++) {
            Layer layer = new Layer(neuralNetwork1.getLayer(i), neuralNetwork2.getLayer(i));
            layers.add(layer);
        }
    }

    double[] run(double[] inputs) {
        for (Layer layer : layers) {
            inputs = layer.run(inputs);
        }
        return inputs;
    }

    void fillRandomWeights() {
        for (Layer layer : layers) {

            for (int j = 0; j < layer.size(); j++) {

                for (int k = 0; k < layer.getPerceptron(j).getWeightsNum(); k++) {

                    double value = 2 * Math.random() - 1;
                    layer.getPerceptron(j).setWeight(k, value);
                }
            }
        }
    }

    public void setAllBiases(double value) {
        for (Layer layer : layers) {

            for (int j = 0; j < layer.size(); j++) {

                for (int k = 0; k < layer.getPerceptron(j).getWeightsNum(); k++) {

                    layer.getPerceptron(j).setWeight(k, value);
                }
            }
        }
    }

    void setBias(double bias) {
        for (int i = 0; i < layers.size(); i++) {

            for (int j = 0; j < layers.get(i).size(); j++) {

                layers.get(i).getPerceptron(j).setBias(bias);
            }
        }
    }

    void show() {

        System.out.println();
        System.out.println("nn hashCode: " + this.hashCode());

        for (int i = 0; i < layers.size(); i++) {
            System.out.println("layer " + i);
            layers.get(i).show();
        }
    }

    String showString() {

        StringBuilder result = new StringBuilder();
        result.append("nn hashCode: ").append(this.hashCode());
        for (int i = 0; i < layers.size(); i++) {
            result.append("\nlayer ").append(i).append(": \n");
            result.append(layers.get(i).showString());
        }
        return result.toString();
    }

    Layer getLayer(int i) {
        return layers.get(i);
    }

    int getLayersNum() {
        return layers.size();
    }

    void mutation(double amount, double percent) {
        for (Layer layer : layers) {
            layer.mutation(amount, percent);
        }
    }

    NeuralNetwork copy() {
        NeuralNetwork newNeuralNetwork = new NeuralNetwork();

        newNeuralNetwork.layers = new ArrayList<>();

        for (int i = 0; i < this.layers.size(); i++) {
            newNeuralNetwork.layers.add(new Layer(this.layers.get(i)));
        }

        return newNeuralNetwork;
    }
}
