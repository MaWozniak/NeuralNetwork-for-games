package NeuralNetwork;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Generation {

    List<Genome> genomes = new ArrayList<Genome>();
    int number;

    public Generation(int number, int size, NeuralNetwork protoplast) {
        this.number = number;
        for (int i = 0; i < size; i++) {
            genomes.add(new Genome(number, protoplast.copy(), "generic"));
        }
    }

    public Generation(int number, int size, List<Genome> ancestors) {
        this.number = number;
        for (int i = 0; i < size; i++) {
            int x = i % ancestors.size();
            String parentage = "1-parent-copy:" + ancestors.get(x).getId();
            genomes.add(new Genome(number, ancestors.get(x).getNeuralNetwork().copy(), parentage));
        }

    }

    public Generation(int number, int size, List<Genome> ancestors, String type) {
        this.number = number;
        double[] val = new double[8];
        if (type.equals("type1")) {
            val = new double[]{0.5, 0.1, 0.2, 0.05, 0.6, 0.2, 0.2, 0.1};
        }
        if (type.equals("type2")) {
            val = new double[]{0.6, 0.4, 0.5, 0.1, 0.8, 0.4, 0.3, 0.15};
        }
        if (type.equals("type3")) {
            val = new double[]{0.05, 0.1, 0.1, 1.5, 0.2, 0.2, 0.2, 0.9};
            //best at the moment:
            //val = new double[]{0.05, 0.1, 0.1, 1.5, 0.5, 0.2, 0.5, 0.9};
            //val = new double[]{0.1, 0.1, 0.1, 1.2, 0.5, 0.15, 0.35, 0.9};
            //best at the moment:
            //val = new double[]{0.05, 0.1, 0.5, 1.5, 0.9, 0.25, 0.5, 0.9};
            //val = new double[]{0.15, 0.1, 0.5, 0.1, 0.2, 0.25, 0.3, 0.9};
            //val = new double[]{0.1, 0.01, 0.5, 0.05, 0.5, 0.1, 0.2, 0.5};
            //val = new double[]{0.05, 0.01, 0.05, 0.02, 0.1, 0.01, 0.5, 0.1};
        }
        for (int i = 0; i < size; i++) {
            if (i % 4 == 0) {
                int x = i % (ancestors.size() / 4);
                String parentage = "1-parent-||" + ancestors.get(x).getId() + "||+mutation(0.5-0.1)";
                NeuralNetwork neuralNetwork = ancestors.get(x).getNeuralNetwork().copy();
                neuralNetwork.mutation(val[0], val[1]);
                genomes.add(new Genome(number, neuralNetwork, parentage));
            }
            if (i % 4 == 1) {
                int x1 = i % (ancestors.size() / 3);
                int x2 = (i - 1) % (ancestors.size() / 3);
                String parentage = "crossover-||" + ancestors.get(x1).getId() + "/" + ancestors.get(x2).getId() + "||+mutation(0.2-0.05)";
                NeuralNetwork neuralNetwork = new NeuralNetwork(ancestors.get(x1).getNeuralNetwork().copy(), ancestors.get(x2).getNeuralNetwork().copy());
                neuralNetwork.mutation(val[2], val[3]);
                genomes.add(new Genome(number, neuralNetwork, parentage));
            }
            if (i % 4 == 2) {
                int x1 = i % (ancestors.size() / 2);
                int x2 = (i - 2) % (ancestors.size() / 2);
                String parentage = "crossover-||" + ancestors.get(x1).getId() + "/" + ancestors.get(x2).getId() + "||+mutation(0.6-0.2)";
                NeuralNetwork neuralNetwork = new NeuralNetwork(ancestors.get(x1).getNeuralNetwork().copy(), ancestors.get(x2).getNeuralNetwork().copy());
                neuralNetwork.mutation(val[4], val[5]);
                genomes.add(new Genome(number, neuralNetwork, parentage));
            }
            if (i % 4 == 3) {
                int x = i % 3;
                String parentage = "1-parent-||" + ancestors.get(x).getId() + "||+mutation(0.2-0.1)";
                NeuralNetwork neuralNetwork = ancestors.get(x).getNeuralNetwork().copy();
                neuralNetwork.mutation(val[6], val[7]);
                genomes.add(new Genome(number, neuralNetwork, parentage));
            }
        }
        //random fellow
        NeuralNetwork neuralNetwork = ancestors.get(1).getNeuralNetwork().copy();
        neuralNetwork.fillRandomWeights();
        genomes.set(45, new Genome(number, neuralNetwork, "random-fellow"));
    }

    public List<Genome> getGenomes() {
        return genomes;
    }

    void show(boolean showNetwork) {
        System.out.println("\nGENERATION " + number + "\n");
        for (Genome genome : this.genomes) {
            genome.show(showNetwork);
        }
    }

    public void randomize() {
        for (Genome genome : this.genomes) {
            genome.getNeuralNetwork().fillRandomWeights();
        }
    }

    public void randomScores() {
        for (Genome genome : this.genomes) {
            genome.setScore(100 * Math.random());
        }
    }

    void sort() {
        genomes.sort(Comparator.comparing(Genome::getScore).reversed());
    }

    public void showScores() {
        this.sort();
        for (Genome genome : this.genomes) {
            System.out.println(genome.getId() + "\tscore: " + genome.getScore());
        }

    }

    void updateScoreToId() {
        for (int i = 0; i < genomes.size(); i++) {
            genomes.get(i).setId(genomes.get(i).getId() + "(" + (i + 1) + ")");
        }
    }

    public List<Genome> bestSelection(int bestNumber) {

        List<Genome> bestSelection = new ArrayList<>();

        this.sort();
        this.updateScoreToId();
        for (int i = 0; i < bestNumber; i++) {
            bestSelection.add(this.genomes.get(i));
        }
        return bestSelection;
    }
}
