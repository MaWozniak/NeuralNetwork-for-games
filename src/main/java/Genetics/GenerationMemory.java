package Genetics;

import Game.PreyAI;
import NeuralNetwork.Genome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class GenerationMemory {

    private List<Genome> genomes;
    private int size;
    private int id;
    private double avarageScore;
    private int countAdding;

    GenerationMemory(int id, int numRememberedPreys) {
        this.genomes = new ArrayList<>();
        this.size = numRememberedPreys;
        this.id = id;
        this.avarageScore = 0.0;
        this.countAdding = 1;
    }

    void add(PreyAI preyAI) {

        if (genomes.size() < size) {
            preyAI.getGenome().setId(preyAI.getId());
            preyAI.getGenome().setScore(preyAI.getScore());
            genomes.add(preyAI.getGenome());
        } else if (preyAI.getScore() > genomes.get(size - 1).getScore()) {
            preyAI.getGenome().setId(preyAI.getId());
            preyAI.getGenome().setScore(preyAI.getScore());
            genomes.set(size - 1, preyAI.getGenome());
        }

        genomes.sort(Comparator.comparing(Genome::getScore).reversed());

        avarageScore = (avarageScore * countAdding + preyAI.getScore()) / (countAdding + 1);
        countAdding++;
    }

    List<Genome> getSelectedGenomes() {
        return genomes;
    }

    Genome get(int i) {
        return genomes.get(i);
    }

    double getAvarageScore() {
        return avarageScore;
    }

    int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }
}

