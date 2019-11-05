package Genetics;

import NeuralNetwork.Genome;

import java.util.ArrayList;
import java.util.List;

class GeneticsMethods {

    private List<Genome> newBorns = new ArrayList<>();

    List<Genome> newGenePool(List<Genome> pastGeneration, int newGenerationSize) {

        for (int i = 0; i < newGenerationSize; i++) {
            newBorns.add(pastGeneration.get(i % 5));
        }
        return newBorns;
    }
}
