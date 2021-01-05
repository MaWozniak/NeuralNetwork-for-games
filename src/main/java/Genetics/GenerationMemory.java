package Genetics;

import Game.Organisms.Prey;
import NeuralNetwork.Genome;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class GenerationMemory {
    private List<Genome> genomes = new ArrayList();
    private int size;
    private int id;
    private double avarageScore;
    private int countAdding;

    GenerationMemory(int id, int numRememberedPreys) {
        this.size = numRememberedPreys;
        this.id = id;
        this.avarageScore = 0.0;
        this.countAdding = 1;
    }

    void add(Prey prey) {
        if (!genomes.contains(prey.getGenome())) {
            if (genomes.size() < size) {
                prey.getGenome().setId(prey.getId());
                prey.getGenome().setScore(prey.getScore());
                genomes.add(prey.getGenome());
            } else if (prey.getScore() > genomes.get(size - 1).getScore()) {
                prey.getGenome().setId(prey.getId());
                prey.getGenome().setScore(prey.getScore());
                genomes.set(size - 1, prey.getGenome());
            }

            genomes.sort(Comparator.comparing(Genome::getScore).reversed());

        }

        avarageScore = (avarageScore * countAdding + prey.getScore()) / (countAdding + 1);
        countAdding++;
    }

    List<Genome> getSelectedGenomes() {
        return genomes;
    }

    Genome get(int i) {
        return genomes.get(i);
    }

    public double getAvarageScore() {
        return avarageScore;
    }

    int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }
}

