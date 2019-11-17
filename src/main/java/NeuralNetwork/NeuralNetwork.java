package NeuralNetwork;

class NeuralNetwork {

    private double[] inputs;
    private double[] outputs;
    private double[][] layers;
    private int numOfLayers;
    private Genome genome;

    NeuralNetwork(int inputs, int outputs, int layers, int neurons, boolean bias) {

        this.inputs = new double[inputs];
        this.outputs = new double[outputs];
        this.layers = new double[layers][neurons];
        this.numOfLayers = layers;
        genome = new Genome(layers, neurons, inputs, outputs, bias);
    }

    void injectGenome(Genome genome) {
        this.genome = genome;
    }

    Genome getGenome() {
        return genome;
    }

    double[] generate(double[] inputs) {

        this.inputs = inputs;
        calculate(this.inputs, layers[0], genome.getWeights()[0], genome.getBiases()[0]);
        for (int i = 1; i < numOfLayers; i++) {
            calculate(layers[i - 1], layers[i], genome.getWeights()[i], genome.getBiases()[i]);
        }
        calculate(layers[numOfLayers - 1], outputs, genome.getWeights()[numOfLayers + 1], genome.getBiases()[numOfLayers + 1]);
        return outputs;
    }

    private void calculate(double[] layer1, double[] layer2, double[][] weights, double[] bias) {

        for (int i = 0; i < layer2.length; i++) {
            layer2[i] = 0.0;
            for (int j = 0; j < layer1.length; j++) {
                layer2[i] += weights[j][i] * layer1[j];
            }
            //ADD BIAS
            // layer2[i] += bias[i];

            // ACTIVATION FUNCTION: Sigmoid Function // Try also STEP FUNCTION & SINUS
            //layer2[i] = 1 / (1 + Math.exp(layer2[i]));

            // ACTIVATION FUNCTION: Step
            if (layer2[i] > 0) {
                layer2[i] = 1;
            } else {
                layer2[i] = 0;
            }
        }

    }
}
