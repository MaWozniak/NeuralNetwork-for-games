package NeuralNetwork;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Generation {
    List<Genome> genomes = new ArrayList();
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

    public Generation(int number, int size, List<Genome> ancestors, String type, double mutationRate) {
        int numOfParties = (int) (size / 10);
        int numOfTheRest = size - 10 * numOfParties;
        this.number = number;
        double[] val = new double[8];
        int i;
        String parentage;
        String parentage1;
        String parentage3;
        String parentage4;
        String parentage5;

        NeuralNetwork neuralNetwork;
        NeuralNetwork neuralNetwork1;
        NeuralNetwork neuralNetwork3;
        NeuralNetwork neuralNetwork4;
        NeuralNetwork neuralNetwork5;
        NeuralNetwork neuralNetwork6;
        NeuralNetwork neuralNetwork7;
        NeuralNetwork neuralNetwork8;
        NeuralNetwork neuralNetwork9;
        NeuralNetwork neuralNetwork10;

        if (type.equals("top5simply")) {
            for (i = 0; i < numOfParties; ++i) {
                parentage1 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||-elite";
                neuralNetwork1 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                this.genomes.add(new Genome(number, neuralNetwork1, parentage1));

                parentage = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationSmSm(0.05-0.05)";
                neuralNetwork = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork.mutation(0.05D, 0.05D);
                this.genomes.add(new Genome(number, neuralNetwork, parentage));

                neuralNetwork3 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork3.mutation(0.05D, 0.05D);
                this.genomes.add(new Genome(number, neuralNetwork3, parentage));

                neuralNetwork4 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork4.mutation(0.05D, 0.05D);
                this.genomes.add(new Genome(number, neuralNetwork4, parentage));

                parentage3 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationBgSm(0.6-0.03)";
                neuralNetwork5 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork5.mutation(0.6D, 0.03D);
                this.genomes.add(new Genome(number, neuralNetwork5, parentage3));

                neuralNetwork6 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork6.mutation(0.6D, 0.03D);
                this.genomes.add(new Genome(number, neuralNetwork6, parentage3));

                neuralNetwork7 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork7.mutation(0.6D, 0.03D);
                this.genomes.add(new Genome(number, neuralNetwork7, parentage3));

                parentage4 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationSmBg(0.05-1.0)";
                neuralNetwork8 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork8.mutation(0.1D, 1.0D);
                this.genomes.add(new Genome(number, neuralNetwork8, parentage4));

                neuralNetwork9 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork9.mutation(0.1D, 1.0D);
                this.genomes.add(new Genome(number, neuralNetwork9, parentage4));

                parentage5 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationMeBg(0.4-1.0)";
                neuralNetwork10 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork10.mutation(0.4D, 1.0D);
                this.genomes.add(new Genome(number, neuralNetwork10, parentage5));
            }
            if (numOfTheRest > 0) {
                for (int j = 0; j < numOfTheRest; j++) {
                    String parentageRandom = "restOf-randomize";
                    NeuralNetwork neuralNetworkForRest = ((Genome) ancestors.get(j)).getNeuralNetwork().copy();
                    neuralNetworkForRest.mutation(0.5, 0.5);
                    this.genomes.add(new Genome(number, neuralNetworkForRest, parentageRandom));
                }
            }
        } else if (type.equals("top5smallMutation")) {
            for (i = 0; i < numOfParties; ++i) {
                parentage1 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||-elite";
                neuralNetwork1 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                this.genomes.add(new Genome(number, neuralNetwork1, parentage1));

                parentage = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationSmSm(0.05-0.05)";
                neuralNetwork = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork.mutation(0.05D, 0.05D);
                this.genomes.add(new Genome(number, neuralNetwork, parentage));

                neuralNetwork3 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork3.mutation(0.05D, 0.05D);
                this.genomes.add(new Genome(number, neuralNetwork3, parentage));

                neuralNetwork4 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork4.mutation(0.05D, 0.05D);
                this.genomes.add(new Genome(number, neuralNetwork4, parentage));

                neuralNetwork10 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork10.mutation(0.05D, 0.05D);
                this.genomes.add(new Genome(number, neuralNetwork10, parentage));

                parentage3 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationBgSm(0.5-0.03)";
                neuralNetwork6 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork6.mutation(0.5D, 0.03D);
                this.genomes.add(new Genome(number, neuralNetwork6, parentage3));

                neuralNetwork7 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork7.mutation(0.5D, 0.03D);
                this.genomes.add(new Genome(number, neuralNetwork7, parentage3));

                neuralNetwork7 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork7.mutation(0.5D, 0.03D);
                this.genomes.add(new Genome(number, neuralNetwork7, parentage3));

                neuralNetwork8 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork8.mutation(0.5D, 0.03D);
                this.genomes.add(new Genome(number, neuralNetwork8, parentage3));

                parentage4 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationSmBg(0.05-1.0)";
                neuralNetwork8 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork8.mutation(0.1D, 1.0D);
                this.genomes.add(new Genome(number, neuralNetwork8, parentage4));
            }
            if (numOfTheRest > 0) {
                for (int j = 0; j < numOfTheRest; j++) {
                    String parentageRandom = "restOf-randomize";
                    NeuralNetwork neuralNetworkForRest = ((Genome) ancestors.get(j)).getNeuralNetwork().copy();
                    neuralNetworkForRest.mutation(0.5, 0.5);
                    this.genomes.add(new Genome(number, neuralNetworkForRest, parentageRandom));
                }
            }
        } else if (type.equals("top5bigRandom")) {
            for (i = 0; i < numOfParties; ++i) {
                parentage1 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||-elite";
                neuralNetwork1 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                this.genomes.add(new Genome(number, neuralNetwork1, parentage1));

                parentage = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationSmSmRandom";
                neuralNetwork = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork.mutation(0.05D, 0.05D);
                this.genomes.add(new Genome(number, neuralNetwork, parentage));

                neuralNetwork3 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork3.mutation(0.1D, 0.05D);
                this.genomes.add(new Genome(number, neuralNetwork3, parentage));

                neuralNetwork4 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork4.mutation(0.05D, 0.1D);
                this.genomes.add(new Genome(number, neuralNetwork4, parentage));

                parentage3 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationBgRandom";
                neuralNetwork5 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork5.mutation(0.6D, 0.1D);
                this.genomes.add(new Genome(number, neuralNetwork5, parentage3));

                neuralNetwork6 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork6.mutation(0.6D, 0.2D);
                this.genomes.add(new Genome(number, neuralNetwork6, parentage3));

                neuralNetwork7 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork7.mutation(0.6D, 0.3D);
                this.genomes.add(new Genome(number, neuralNetwork7, parentage3));

                parentage4 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationSmBg(0.1/0.2-1.0)";
                neuralNetwork8 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork8.mutation(0.1D, 1.0D);
                this.genomes.add(new Genome(number, neuralNetwork8, parentage4));

                neuralNetwork9 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork9.mutation(0.2D, 1.0D);
                this.genomes.add(new Genome(number, neuralNetwork9, parentage4));

                parentage5 = "1-parent-||" + ((Genome) ancestors.get(i)).getId() + "||+mutationMeBg(0.4-1.0)";
                neuralNetwork10 = ((Genome) ancestors.get(i)).getNeuralNetwork().copy();
                neuralNetwork10.mutation(0.4D, 1.0D);
                this.genomes.add(new Genome(number, neuralNetwork10, parentage5));
            }
            if (numOfTheRest > 0) {
                for (int j = 0; j < numOfTheRest; j++) {
                    String parentageRandom = "restOf-randomize";
                    NeuralNetwork neuralNetworkForRest = ((Genome) ancestors.get(j)).getNeuralNetwork().copy();
                    neuralNetworkForRest.mutation(0.5, 0.5);
                    this.genomes.add(new Genome(number, neuralNetworkForRest, parentageRandom));
                }
            }
        } else {
            if (type.equals("type1")) {
                val = new double[]{0.5D, 0.1D, 0.2D, 0.05D, 0.6D, 0.2D, 0.2D, 0.1D};
            }

            if (type.equals("type2")) {
                val = new double[]{0.6D, 0.4D, 0.5D, 0.1D, 0.8D, 0.4D, 0.3D, 0.15D};
            }

            if (type.equals("type3")) {
                val = new double[]{0.1D, 0.03D, 0.1D, 0.05D, 0.3D, 0.05D, 0.1D, mutationRate / 2.0D};
            }

            for (i = 0; i < size; ++i) {
                int x;

                if (i % 4 == 0) {
                    x = i % (ancestors.size() / 4);
                    parentage = "1-parent-||" + ((Genome) ancestors.get(x)).getId() + "||+mutation()";
                    neuralNetwork = ((Genome) ancestors.get(x)).getNeuralNetwork().copy();
                    neuralNetwork.mutation(val[0], val[1]);
                    this.genomes.add(new Genome(number, neuralNetwork, parentage));
                }

                int x2;
                if (i % 4 == 1) {
                    x = i % (ancestors.size() / 3);
                    x2 = (i - 1) % (ancestors.size() / 3);
                    parentage = "crossover-||" + ((Genome) ancestors.get(x)).getId() + "/" + ((Genome) ancestors.get(x2)).getId() + "||+mutation()";
                    neuralNetwork = new NeuralNetwork(((Genome) ancestors.get(x)).getNeuralNetwork().copy(), ((Genome) ancestors.get(x2)).getNeuralNetwork().copy());
                    neuralNetwork.mutation(val[2], val[3]);
                    this.genomes.add(new Genome(number, neuralNetwork, parentage));
                }

                if (i % 4 == 2) {
                    x = i % (ancestors.size() / 2);
                    x2 = (i - 2) % (ancestors.size() / 2);
                    parentage = "crossover-||" + ((Genome) ancestors.get(x)).getId() + "/" + ((Genome) ancestors.get(x2)).getId() + "||+mutation()";
                    neuralNetwork = new NeuralNetwork(((Genome) ancestors.get(x)).getNeuralNetwork().copy(), ((Genome) ancestors.get(x2)).getNeuralNetwork().copy());
                    neuralNetwork.mutation(val[4], val[5]);
                    this.genomes.add(new Genome(number, neuralNetwork, parentage));
                }

                if (i % 4 == 3) {
                    x = i % 3;
                    parentage = "1-parent-||" + ((Genome) ancestors.get(x)).getId() + "||+mutation()";
                    neuralNetwork = ((Genome) ancestors.get(x)).getNeuralNetwork().copy();
                    neuralNetwork.mutation(val[6], val[7]);
                    if (Math.random() < 0.1D) {
                        neuralNetwork.mutation(0.15D, 1.0D);
                    }

                    this.genomes.add(new Genome(number, neuralNetwork, parentage));
                }
            }
        }

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
        for (int i = 0; i < genomes.size(); ++i) {
            (genomes.get(i)).setId((genomes.get(i)).getId() + "--(" + (i + 1) + ")--");
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
