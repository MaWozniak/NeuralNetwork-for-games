package NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NodalNetwork {
    private List<NeuralNetwork> networks = new ArrayList();
    private NeuralNetwork bridge;
    private NeuralNetwork node;

    public NodalNetwork(NeuralNetwork bridge, NeuralNetwork node, int numOfNodes) {
        this.bridge = bridge;
        this.node = node;

        for (int i = 0; i < numOfNodes; i++) {
            NeuralNetwork oneNode = node.copy();
            networks.add(oneNode);
        }
    }

    public NodalNetwork(NeuralNetwork bridge, NeuralNetwork node, List<NeuralNetwork> networks) {
        this.bridge = bridge;
        this.node = node;
        this.networks = networks;

    }

    double[] run(double[] inputs, int workingNetwork) {
        //smart comment-log
//        if(Math.random()<0.001) {
//            System.out.println("--------------------NodalNetwork.run wokingNetwork: " + workingNetwork + "-----------------");
//        }
        if (workingNetwork == -1) {
            double[] bridgeOutput = bridge.runNoActivationFunc(inputs);
            int selectedNetwork = pointMaxInput(bridgeOutput);

            return networks.get(selectedNetwork).run(inputs);
        }
        return networks.get(workingNetwork).run(inputs);
    }

    private int pointMaxInput(double[] inputs) {
        int maximumInput = 0;
        double indicator = -2.0;
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] > indicator) {
                indicator = inputs[i];
                maximumInput = i;
            }
        }
        return maximumInput;
    }

    NodalNetwork copy() {
        List<NeuralNetwork> newNodalNetworkNodes = new ArrayList<>();
        for (NeuralNetwork network : this.networks) {
            newNodalNetworkNodes.add(network.copy());
        }
        return new NodalNetwork(this.bridge.copy(), this.node.copy(), newNodalNetworkNodes);
    }

    void show() {
        System.out.println();
        System.out.println("nodal-nn hashCode: " + this.hashCode());
        System.out.print("bridge: ");
        this.bridge.show();
        System.out.print("node: ");
        this.node.show();

        for (NeuralNetwork network : networks) {
            System.out.print("network node: ");
            network.show();
            System.out.println();
        }

    }

    String showString() {
        StringBuilder result = new StringBuilder();
        result.append("nodal-nn hashCode: ").append(this.hashCode());
        return result.toString();
    }

    public NeuralNetwork getNetwork(int num) {
        if (num == -1) return bridge;
        else
            return networks.get(num);
    }

    int getNumOfNetworks() {
        return networks.size();
    }

    public void setNetwork(NeuralNetwork network, int num) {
        this.networks.set(num, network);
    }
}
