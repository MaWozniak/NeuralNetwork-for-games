package manager;

import game.organisms.Prey;
import game.stage.StageManager;
import neuralnetwork.Generation;
import neuralnetwork.Genome;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.NodalNetwork;

import java.text.DecimalFormat;
import java.util.List;

public class GenerationManager {
    private static final DecimalFormat DF_2 = new DecimalFormat("#.#");
    private static final int RESET_GENERATION = 50000000;

    private Generation generation;
    private final StageManager stageManager;
    private final GenerationScore generationScore;
    private GenerationMemory generationMemory;
    private final List<Prey> AI_prey;
    private final int generationSize;
    private int count = 1;
    private double mutationRate;
    private int index = 0;
    private final double preyMaxAge;
    private boolean firstInGeneration;


    public GenerationManager(List<Prey> AI_prey, int size, double preyMaxAge, StageManager stageManager) {
        this.generationSize = size;
        this.stageManager = stageManager;
        NeuralNetwork progenitor = new NeuralNetwork(25, 3, 30, 4);
        double BIAS = 1.0;
        progenitor.setAllBiases(BIAS);
        NeuralNetwork bridge = new NeuralNetwork(25, 3, 25, 3);
        NodalNetwork nodalNetworkProgenitor = new NodalNetwork(bridge, progenitor, 3);

        this.generation = new Generation(1, generationSize, nodalNetworkProgenitor);
        this.AI_prey = AI_prey;
        this.mutationRate = 1.1;
        this.preyMaxAge = preyMaxAge;
        this.generationScore = new GenerationScore();
    }

    public void addFirstGeneration() {
        generationScore.log("start", count, stageManager, generationMemory);
        randomGeneration();
        generationMemory = new GenerationMemory(count, 10);
        count++;
    }

    public void addNewGeneration() {
        generationScore.addNewGeneration(count, stageManager, generationMemory);

        if (count % RESET_GENERATION == 0) {
            randomGeneration();
        } else {
            int numOfBestSelection = generationSize / 10;
            geneticsNewGeneration(generation.bestSelection(numOfBestSelection));
        }

        generationMemory.getSelectedGenomes().clear();
        generationMemory = new GenerationMemory(count, 10);
        generationScore.log("start", count, stageManager, generationMemory);
        count++;
    }

    private void randomGeneration() {
        generation.randomize();

        for (int i = 0; i < generationSize; i++) {
            double xStartPos = 600 + 600 * (Math.random() - 0.5);
            double yStartPos = 400 + 600 * (Math.random() - 0.5);
            if (i == 0) {
                firstInGeneration = true;
            }

            Prey newPrey = new Prey(xStartPos, yStartPos, generation.getGenomes().get(i), firstInGeneration, preyMaxAge);
            AI_prey.add(newPrey);
            firstInGeneration = false;
        }

    }

    private void geneticsNewGeneration(List<Genome> ancestors) {
        double newMutationRate = generationScore.getNewMutationRate();
        setMutationRate(newMutationRate);
        generation = new Generation(
                count,
                generationSize,
                ancestors,
                getGeneticsType(),
                stageManager.getStage(count - 1),
                newMutationRate
        );

        for (int i = 0; i < generationSize; i++) {
            double dispersion = 500;
            double xStartPos = 600 + dispersion * (Math.random() - 0.5);
            double yStartPos = 400 + dispersion * (Math.random() - 0.5);
            if (i == 0) {
                firstInGeneration = true;
            }

            Prey newPrey = new Prey(xStartPos, yStartPos, generation.getGenomes().get(i), firstInGeneration, preyMaxAge);
            newPrey.setId(i, count);
            AI_prey.add(newPrey);
            firstInGeneration = false;
        }

    }

    public String getGeneticsType() {
        String[] array = {"top5simply", "top5smallMutation", "top5bigRandom", "type3"};
        if (count % RESET_GENERATION == 0) {
            ++index;
        }
        if (index > array.length - 1) {
            index = 0;
        }
        System.out.println("-----------------USES GENETICS TYPE: ..." + array[index]
                + "...---------------------");
        return array[index];
    }

    public void deathPrey(Prey prey) {
        if (prey.isAlive()) {
            prey.getGenome().setScore(prey.getScore());
            System.out.print(DF_2.format(prey.getGenome().getScore()) + "|");
            generationMemory.add(prey);
            prey.isDead();
            prey.setFirstInGeneration(false);
        }

    }

    public String getMutationRate() {
        return DF_2.format(mutationRate);
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getCount() {
        return count;
    }

    public List<Double> getGenerationsScoresList() {
        return generationScore.getGenerationsScoresList();
    }

    public List<Double> getGenerationsAverageList() {
        return generationScore.getGenerationsAverageList();
    }
}
