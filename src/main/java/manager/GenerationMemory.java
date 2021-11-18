package manager;

import game.organisms.Prey;
import neuralnetwork.Genome;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class GenerationMemory {
    private List<Genome> genomes = new LinkedList<>();
    private final int size;
    private final int id;
    private double averageScore;
    private int countAdding;

    GenerationMemory(int id, int numRememberedPreys) {
        this.size = numRememberedPreys;
        this.id = id;
        this.averageScore = 0.0;
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

        averageScore = (averageScore * countAdding + prey.getScore()) / (countAdding + 1);
        countAdding++;
    }

    List<Genome> getSelectedGenomes() {
        return genomes;
    }

    private void setGenomes(List<Genome> genomes) {
        List<Genome> newList = new LinkedList<>();
        for (Genome genome : genomes) {
            newList.add(genome.copy());
        }
        this.genomes = newList;
    }

    Genome get(int i) {
        return genomes.get(i);
    }

    public double getAverageScore() {
        return averageScore;
    }

    int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public GenerationMemory copy() {
        GenerationMemory newGenMem = new GenerationMemory(this.id, this.size);
        newGenMem.averageScore = this.averageScore;
        newGenMem.countAdding = this.countAdding;
        newGenMem.setGenomes(new LinkedList<>(this.genomes));
        return newGenMem;
    }
}

