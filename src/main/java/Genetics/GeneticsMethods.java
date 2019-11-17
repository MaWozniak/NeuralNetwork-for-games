package Genetics;

import NeuralNetwork.Genome;

import java.util.ArrayList;
import java.util.List;

class GeneticsMethods {

    private List<Genome> newBorns = new ArrayList<>();
    private int numSelectionPlusMutation = 25;
    private int numOnlySelection = 5;
    private int numOnlyMutation = 20;
    private int numNewRandom = 5;
    private int neuNetNumLeyers = 2;
    private int neuNetNeurons = 9;
    private int neuNetInputs = 9;
    private int neuNetOutputs = 4;

    List<Genome> newGenePool(List<Genome> pastGeneration, int newGenerationSize) {

        newBorns.clear();
        for (int i = 0; i < 50; i++) {
            newBorns.add(new Genome(neuNetNumLeyers, neuNetNeurons, neuNetInputs, neuNetOutputs, true));
        }
        //        selectionPlusMutation(pastGeneration, numSelectionPlusMutation);
        //        onlySelection(pastGeneration, numOnlySelection);
        //        onlyMutation(pastGeneration, numOnlyMutation, 0.5);
        //        onlyMutation(pastGeneration, numOnlyMutation, 3.33);
        //        randomize(numNewRandom);

        strongMutation(pastGeneration);
        strongSelection(pastGeneration);

        for (Genome newBorn : newBorns) {
            System.out.println(newBorn.getId());
        }

        return newBorns;
    }

    private void strongMutation(List<Genome> pastGeneration) {
        for (int i = 0; i < 10; i++) {
            newBorns.get(i).clone(mutation(pastGeneration.get(0), 5 * Math.random()));
            newBorns.get(i).setId("Smut-" + 0 + "-" + i);
        }
        for (int i = 0; i < 10; i++) {
            newBorns.get(i + 10).clone(mutation(pastGeneration.get(1), 10 * Math.random()));
            newBorns.get(i + 10).setId("Smut-" + 1 + "-" + i);
        }
        for (int i = 0; i < 5; i++) {
            newBorns.get(i + 20).clone(mutation(pastGeneration.get(2), 15 * Math.random()));
            newBorns.get(i + 20).setId("Smut-" + 2 + "-" + i);
        }
        for (int i = 0; i < 5; i++) {
            newBorns.get(i + 25).clone(mutation(pastGeneration.get(3), 20 * Math.random()));
            newBorns.get(i + 25).setId("Smut-" + 3 + "-" + i);
        }
        for (int i = 0; i < 5; i++) {
            newBorns.get(i + 30).clone(mutation(pastGeneration.get(4), 25 * Math.random()));
            newBorns.get(i + 30).setId("Smut-" + 4 + "-" + i);
        }
        for (int i = 0; i < 5; i++) {
            newBorns.get(i + 35).clone(mutation(pastGeneration.get(5), 30 * Math.random()));
            newBorns.get(i + 35).setId("Smut-" + 5 + "-" + i);
        }
    }

    private void strongSelection(List<Genome> pastGeneration) {
        for (int i = 0; i < 10; i++) {
            newBorns.get(i + 40).clone(selection(pastGeneration.get(0), pastGeneration.get(1)));
            newBorns.get(i + 40).setId("Ssel-" + i);
        }
    }

    private void selectionPlusMutation(List<Genome> pastGeneration, int num) {

        for (int i = 0; i < 5; i++) {
            Genome parent1 = pastGeneration.get(i);
            for (int j = i; j < 9 - i; j++) {
                Genome parent2 = pastGeneration.get(j);
                newBorns.add(selectMutation(parent1, parent2));
            }
        }
    }

    private void onlySelection(List<Genome> pastGeneration, int num) {
        for (int i = 0; i < 3; i++) {
            Genome parent1 = pastGeneration.get(i);
            for (int j = i; j < 4; j++) {
                Genome parent2 = pastGeneration.get(j);
                newBorns.add(selection(parent1, parent2));
            }
        }
    }

    private void onlyMutation(List<Genome> pastGeneration, int num, double percent) {
        for (int i = 0; i < 4; i++) {
            Genome parent = pastGeneration.get(i);
            for (int j = 0; j < 4 - i; j++) {
                newBorns.add(mutation(parent, percent));
            }
        }
    }

    private void randomize(int num) {
        for (int i = 0; i < num; i++) {
            Genome newGenome = new Genome(neuNetNumLeyers, neuNetNeurons, neuNetInputs, neuNetOutputs, true);
            newGenome.generateWeights(neuNetNumLeyers, neuNetNeurons, neuNetInputs, neuNetOutputs);
            newGenome.generateBiases(neuNetNumLeyers, neuNetNeurons, neuNetInputs, neuNetOutputs);
            newGenome.setId("");
            newGenome.appendId("-RANDOM-");
            newBorns.add(newGenome);
        }
    }

    private Genome selectMutation(Genome parent1, Genome parent2) {
        Genome newGenome = selection(parent1, parent2);
        return mutation(newGenome, 2.0);
    }

    private Genome selection(Genome parent1, Genome parent2) {
        Genome newGenome = new Genome(neuNetNumLeyers, neuNetNeurons, neuNetInputs, neuNetOutputs, true);
        for (int n = 0; n < parent1.getWeights()[0][1].length; n++) {

            for (int i = 0; i < parent1.getWeights().length; i++) {

                for (int j = 0; j < parent1.getWeights()[0].length; j++) {
                    double x = Math.random();
                    if (x > +0.5) {
                        newGenome.setWeight(i, j, n, parent1.getWeights()[i][j][n]);
                    } else {
                        newGenome.setWeight(i, j, n, parent2.getWeights()[i][j][n]);
                    }

                }
            }
        }
        for (int i = 0; i < parent1.getBiases().length; i++) {
            for (int j = 0; j < parent1.getBiases()[0].length; j++) {
                double x = Math.random();
                if (x > +0.5) {
                    newGenome.setBias(i, j, parent2.getBiases()[i][j]);
                } else {
                    newGenome.setBias(i, j, parent1.getBiases()[i][j]);
                }
            }
        }
        newGenome.setId("");
        newGenome.appendId("-SEL-p1:" + parent1.getId() + "-p2:" + parent2.getId() + "-");
        return newGenome;
    }

    private Genome mutation(Genome genome, double percent) {
        for (int n = 0; n < genome.getWeights()[0][1].length; n++) {

            for (int i = 0; i < genome.getWeights().length; i++) {

                for (int j = 0; j < genome.getWeights()[0].length; j++) {
                    double x = 100 * Math.random();
                    if (x > +percent) {
                        double changeValue = 2 * Math.random() - 1;
                        genome.setWeight(i, j, n, changeValue);
                    }
                }
            }
        }
        for (int i = 1; i < genome.getBiases().length; i++) {
            for (int j = 0; j < genome.getBiases()[0].length; j++) {
                double x = 100 * Math.random();
                if (x > +percent) {
                    //double changeValue = 2 * Math.random() - 1;
                    double changeValue = 1.0;
                    genome.setBias(i, j, changeValue);
                }
            }
        }
        genome.appendId("-MUT:" + percent + "-");
        return genome;
    }
}
