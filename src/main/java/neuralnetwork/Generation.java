package neuralnetwork;

import game.stage.Stage;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Generation {
    List<Genome> genomes = new LinkedList<>();
    int number;
    Stage stage;
    int learningNetwork;

    public Generation(int number, int size, NodalNetwork protoplast) {
        this.number = number;
        for (int i = 0; i < size; i++) {
            genomes.add(new Genome(number, protoplast.copy(), "generic", learningNetwork));
        }

    }

    public Generation(int number, int size, List<Genome> ancestors) {
        this.number = number;
        for (int i = 0; i < size; i++) {
            int x = i % ancestors.size();
            String parentage = "1-parent-copy:" + ancestors.get(x).getId();
            genomes.add(new Genome(number, ancestors.get(x).getNodalNetwork().copy(), parentage, learningNetwork));
        }

    }

    public Generation(int number, int size, List<Genome> ancestors, String type, Stage stage, double mutationRate) {
        int numOfParties = size / 10;
        int numOfTheRest = size - 10 * numOfParties;
        this.stage = stage;
        this.learningNetwork = stage.getLearningNetwork();
        //log
        System.out.println("----------------Generation constructor: this.learningNetwork = stage.getLearningNetwork(); " + stage.getLearningNetwork());
        this.number = number;
        double[] val = new double[8];
        int i;
        switch (type) {
            case "top5simply":
                for (i = 0; i < numOfParties; ++i) {
                    createGenome(ancestors.get(i), "elite");
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationBgSm(0.4-0.03)", 0.4, 0.03);
                    createMutatedGenome(ancestors.get(i), "mutationBgSm(0.5-0.02)", 0.5, 0.02);
                    createMutatedGenome(ancestors.get(i), "mutationBgSm(0.6-0.01)", 0.6, 0.01);
                    //createMutatedGenome(ancestors.get(i), "mutationNewMeMe(0.1-0.3)", 0.1, 0.3);
                    createMutatedGenome(ancestors.get(i), "mutationNewSmMe(0.05-0.1)", 0.05, 0.1);
                    createMutatedGenome(ancestors.get(i), "mutationNewMeSm(0.1-0.01)", 0.1, 0.01);
                    createMutatedGenome(ancestors.get(i), "mutationNewSmBg(0.05-0.3)", 0.05, 0.3);
                }
                if (numOfTheRest > 0) {
                    for (int j = 0; j < numOfTheRest; j++) {
                        createMutatedGenome(ancestors.get(j), "rest-of-randomize", 0.5, 0.5);
                    }
                }
                break;
            case "top5smallMutation":
                for (i = 0; i < numOfParties; ++i) {
                    createGenome(ancestors.get(i), "elite");
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationBgSm(0.6-0.03)", 0.5, 0.03);
                    createMutatedGenome(ancestors.get(i), "mutationBgSm(0.6-0.03)", 0.5, 0.03);
                    createMutatedGenome(ancestors.get(i), "mutationBgSm(0.6-0.03)", 0.5, 0.03);
                    createMutatedGenome(ancestors.get(i), "mutationBgSm(0.6-0.03)", 0.5, 0.03);
                    createMutatedGenome(ancestors.get(i), "mutationSmBg(0.05-1.0)", 0.1, 1.0);
                }
                if (numOfTheRest > 0) {
                    for (int j = 0; j < numOfTheRest; j++) {
                        createMutatedGenome(ancestors.get(j), "rest-of-randomize", 0.5, 0.5);
                    }
                }
                break;
            case "top5bigRandom":
                for (i = 0; i < numOfParties; ++i) {
                    createGenome(ancestors.get(i), "elite");
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationSmSm(0.05-0.05)", 0.05, 0.05);
                    createMutatedGenome(ancestors.get(i), "mutationBgRandom", 0.6, 0.1);
                    createMutatedGenome(ancestors.get(i), "mutationBgRandom", 0.6, 0.2);
                    createMutatedGenome(ancestors.get(i), "mutationBgRandom", 0.6, 0.3);
                    createMutatedGenome(ancestors.get(i), "mutationSmBg(0.05-1.0)", 0.1, 1.0);
                    createMutatedGenome(ancestors.get(i), "mutationSmBg(0.05-1.0)", 0.2, 1.0);
                    createMutatedGenome(ancestors.get(i), "mutationMeBg(0.4-1.0)", 0.4, 1.0);
                }
                if (numOfTheRest > 0) {
                    for (int j = 0; j < numOfTheRest; j++) {
                        createMutatedGenome(ancestors.get(j), "rest-of-randomize", 0.5, 0.5);
                    }
                }
                break;
            default:
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
                    int x, x2;
                    if (i % 4 == 0) {
                        x = i % (ancestors.size() / 4);
                        createMutatedGenome(ancestors.get(x), "mutation()", val[0], val[1]);
                    }
                    if (i % 4 == 1) {
                        x = i % (ancestors.size() / 3);
                        x2 = (i - 1) % (ancestors.size() / 3);
                        createCrossoverGenome(ancestors.get(x), ancestors.get(x2), "+mutation()", val[2], val[3]);
                    }
                    if (i % 4 == 2) {
                        x = i % (ancestors.size() / 2);
                        x2 = (i - 2) % (ancestors.size() / 2);
                        createCrossoverGenome(ancestors.get(x), ancestors.get(x2), "+mutation()", val[4], val[5]);
                    }
                    if (i % 4 == 3) {
                        x = i % 3;
                        createMutatedGenome(ancestors.get(x), "mutation()", val[6], val[7]);
                    }
                }
                break;
        }

    }

    private void createGenome(Genome ancestor, String parentMessage) {
        String parentage = "1-parent-||" + ancestor.getId() + "||-" + parentMessage;
        NodalNetwork nodalNetwork = ancestor.getNodalNetwork().copy();
        this.genomes.add(new Genome(number, nodalNetwork, parentage, learningNetwork));
    }

    private void createMutatedGenome(Genome ancestor,
                                     String parentMessage,
                                     double mutationAmount,
                                     double mutationPercent) {
        String parentage = "1-parent-||" + ancestor.getId() + "||-" + parentMessage;
        NodalNetwork nodalNetwork = ancestor.getNodalNetwork().copy();
        nodalNetwork.getNetwork(this.stage.getLearningNetwork()).mutation(mutationAmount, mutationPercent);
        this.genomes.add(new Genome(number, nodalNetwork, parentage, learningNetwork));
    }

    private void createCrossoverGenome(Genome ancestor1,
                                       Genome ancestor2,
                                       String parentMessage,
                                       double mutationAmount,
                                       double mutationPercent) {
        String parentage = "crossover-||" + ancestor1.getId() + "/" + ancestor2.getId() + "||-" + parentMessage;
        NodalNetwork nodalNetwork = ancestor1.getNodalNetwork().copy();
        NeuralNetwork newNeuralNetwork = new NeuralNetwork(
                ancestor1.getNodalNetwork().getNetwork(this.stage.getLearningNetwork()).copy(),
                ancestor2.getNodalNetwork().getNetwork(this.stage.getLearningNetwork()).copy());

        nodalNetwork.getNetwork(this.stage.getLearningNetwork()).mutation(mutationAmount, mutationPercent);
        newNeuralNetwork.mutation(mutationAmount, mutationPercent);
        nodalNetwork.setNetwork(newNeuralNetwork, this.stage.getLearningNetwork());
        this.genomes.add(new Genome(number, nodalNetwork, parentage, learningNetwork));
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
            for (int i = 0; i < genome.getNodalNetwork().getNumOfNetworks(); i++) {
                genome.getNodalNetwork().getNetwork(i).fillRandomWeights();
            }
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

        List<Genome> bestSelection = new LinkedList<>();
        this.sort();
        this.updateScoreToId();

        for (int i = 0; i < bestNumber; i++) {
            bestSelection.add(this.genomes.get(i));
        }

        return bestSelection;
    }
}
