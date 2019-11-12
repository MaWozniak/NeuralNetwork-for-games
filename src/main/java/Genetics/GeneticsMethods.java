package Genetics;

import NeuralNetwork.Genome;

import java.util.ArrayList;
import java.util.List;

class GeneticsMethods {

    private List<Genome> newBorns = new ArrayList<>();
    private int numSelectionPlusMutation = 25;
    private int numOnlySelection = 12;
    private int numOnlyMutation = 10;
    private int numNewRandom = 3;

    List<Genome> newGenePool(List<Genome> pastGeneration, int newGenerationSize) {

        selectionPlusMutation(pastGeneration, numSelectionPlusMutation);
        onlySelection(pastGeneration, numOnlySelection);
        onlyMutation(pastGeneration, numOnlyMutation);
        randomize(numNewRandom);
        return newBorns;
    }

    private void selectionPlusMutation(List<Genome> pastGeneration, int num) {

        for (int i = 0; i < 5; i++) {
            Genome parent1 = pastGeneration.get(i);
            for (int j = i; j < 10 - i; j++) {
                Genome parent2 = pastGeneration.get(j);
                newBorns.add(selectMutation(parent1, parent2));
            }
        }
    }

    private void onlySelection(List<Genome> pastGeneration, int num) {
        for (int i = 0; i < 3; i++) {
            Genome parent1 = pastGeneration.get(i);
            for (int j = i; j < 6; j++) {
                Genome parent2 = pastGeneration.get(j);
                newBorns.add(selection(parent1, parent2));
            }
        }
    }

    private void onlyMutation(List<Genome> pastGeneration, int num) {
        for (int i = 0; i < 5; i++) {
            Genome parent = pastGeneration.get(i);
            for (int j = 0; j < 5 - i; j++) {
                newBorns.add(mutation(parent, 0.6, 0.75));
            }
        }
    }

    private void randomize(int num) {
        for (int i = 0; i < num; i++) {
            Genome newGenome = new Genome(3, 30, 13, 4, true);
            newGenome.generateWeights(3, 30, 13, 4);
            newGenome.generateBiases(3, 30, 13, 4);
            newBorns.add(newGenome);
        }
    }

    private Genome selectMutation(Genome parent1, Genome parent2) {
        Genome newGenome = selection(parent1, parent2);
        return mutation(newGenome, 0.6, 0.35);
    }

    private Genome selection(Genome parent1, Genome parent2) {
        Genome newGenome = new Genome(3, 30, 13, 4, true);
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
        return newGenome;
    }

    private Genome mutation(Genome genome, double percent, double max) {
        for (int n = 0; n < genome.getWeights()[0][1].length; n++) {

            for (int i = 0; i < genome.getWeights().length; i++) {

                for (int j = 0; j < genome.getWeights()[0].length; j++) {
                    double x = Math.random();
                    if (x > +percent) {
                        double changeValue = genome.getWeights()[i][j][n];
                        changeValue += max * (Math.random() - 0.5);
                        genome.setWeight(i, j, n, changeValue);
                    }
                }
            }
        }
        for (int i = 1; i < genome.getBiases().length; i++) {
            for (int j = 0; j < genome.getBiases()[0].length; j++) {
                double x = Math.random();
                if (x > +percent) {
                    double changeValue = genome.getBiases()[i][j];
                    changeValue += max * (Math.random() - 0.5);
                    genome.setBias(i, j, changeValue);
                }
            }
        }
        return genome;
    }
}
