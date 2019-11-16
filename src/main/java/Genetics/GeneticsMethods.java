package Genetics;

import NeuralNetwork.Genome;

import java.util.ArrayList;
import java.util.List;

class GeneticsMethods {

    private List<Genome> newBorns = new ArrayList<>();
    private int numSelectionPlusMutation = 25;
    private int numOnlySelection = 5;
    private int numOnlyMutation = 20;
    private int numNewRandom = 2;
    private int neuNetNumLeyers = 2;
    private int neuNetNeurons = 12;
    private int neuNetInputs = 12;
    private int neuNetOutputs = 4;

    List<Genome> newGenePool(List<Genome> pastGeneration, int newGenerationSize) {

        newBorns.clear();
        selectionPlusMutation(pastGeneration, numSelectionPlusMutation);
        for (int i = 0; i < newBorns.size(); i++) {
            System.out.println(i + " - " + newBorns.get(i).getId());
        }
        onlySelection(pastGeneration, numOnlySelection);
        for (int i = 0; i < newBorns.size(); i++) {
            System.out.println(i + " - " + newBorns.get(i).getId());
        }
        onlyMutation(pastGeneration, numOnlyMutation, 2);
        for (int i = 0; i < newBorns.size(); i++) {
            System.out.println(i + " - " + newBorns.get(i).getId());
        }
        onlyMutation(pastGeneration, numOnlyMutation, 1);
        for (int i = 0; i < newBorns.size(); i++) {
            System.out.println(i + " - " + newBorns.get(i).getId());
        }
        randomize(numNewRandom);
        for (int i = 0; i < newBorns.size(); i++) {
            System.out.println(i + " - " + newBorns.get(i).getId());
        }
        return newBorns;
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
        for (int i = 0; i < 2; i++) {
            Genome parent1 = pastGeneration.get(i);
            for (int j = i; j < 3; j++) {
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
        return mutation(newGenome, 5.0);
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
                    double changeValue = 1;
                    genome.setBias(i, j, changeValue);
                }
            }
        }
        genome.appendId("-MUT:" + percent + "-");
        return genome;
    }
}
