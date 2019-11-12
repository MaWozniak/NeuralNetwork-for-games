package Genetics;

import Game.PreyAI;
import NeuralNetwork.Genome;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Generations {

    private GeneticsMethods geneticsMethods;

    private List<Double> generationsScoresList;
    private GenerationMemory generationMemory;
    private List<PreyAI> AI_prey;
    private int generationSize;
    private int count = 1;

    private double avarageScoreOfAllGenerations = 0.0;
    private double bestScoreOfALLGenerations = 0.0;
    private int indexOfBestGeneration = 0;

    private double bestScore = 0.0;
    private String idOfBestScore = "";
    private double secondBestScore = 0.0;
    private String idOfSecondBestScore = "";
    private double thirdBestScore = 0.0;
    private String idOfthirdBestScore = "";


    public Generations(List<PreyAI> AI_prey, int size) {
        this.geneticsMethods = new GeneticsMethods();
        this.AI_prey = AI_prey;
        this.generationSize = size;
        this.generationsScoresList = new ArrayList<>();
    }

    public void addFirstGeneration() throws IOException {

        log("start");
        randomGeneration();
        generationMemory = new GenerationMemory(count, 10);

        count++;
    }

    public void addNewGeneration() throws IOException {

        updateAvaregeScores();
        updateBestIndividualScores();
        log("summary");

        writeFileLog();

        geneticsNewGeneration(generationMemory.getSelectedGenomes());
        generationMemory = new GenerationMemory(count, 10);

        log("start");
        count++;
    }

    private void randomGeneration() {
        for (int i = 0; i < generationSize; i++) {

            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();
            PreyAI newPreyAI = new PreyAI(xStartPos, yStartPos);
            newPreyAI.setId(i, count);
            AI_prey.add(newPreyAI);
        }
    }

    private void geneticsNewGeneration(List<Genome> genomes) {

        List<Genome> newGenePool = geneticsMethods.newGenePool(genomes, generationSize);

        for (int i = 0; i < generationSize; i++) {

            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();

            //INJECTION GENOMES (!)
            PreyAI newPreyAI = new PreyAI(xStartPos, yStartPos, newGenePool.get(i));
            newPreyAI.setId(i, count);
            AI_prey.add(newPreyAI);
        }
    }

    public void deathPrey(PreyAI preyAI) {
        generationMemory.add(preyAI);
        preyAI.isDead();
    }

    private void updateAvaregeScores() {
        if (avarageScoreOfAllGenerations == 0) {

            avarageScoreOfAllGenerations = generationMemory.getAvarageScore();
        } else {
            avarageScoreOfAllGenerations = (avarageScoreOfAllGenerations * (count - 1) + generationMemory.getAvarageScore()) / (count);
        }

        if (generationMemory.getAvarageScore() > this.bestScoreOfALLGenerations) {

            bestScoreOfALLGenerations = generationMemory.getAvarageScore();
            indexOfBestGeneration = generationMemory.getId();
        }
    }

    private void updateBestIndividualScores() {
        if (generationMemory.get(0).getScore() >= bestScore) {

            thirdBestScore = secondBestScore;
            idOfthirdBestScore = idOfSecondBestScore;

            secondBestScore = bestScore;
            idOfSecondBestScore = idOfBestScore;

            bestScore = generationMemory.get(0).getScore();
            idOfBestScore = generationMemory.get(0).getId();

        } else if (generationMemory.get(0).getScore() >= secondBestScore) {

            thirdBestScore = secondBestScore;
            idOfthirdBestScore = idOfSecondBestScore;

            secondBestScore = generationMemory.get(0).getScore();
            idOfSecondBestScore = generationMemory.get(0).getId();

        } else if (generationMemory.get(0).getScore() >= thirdBestScore) {

            thirdBestScore = generationMemory.get(0).getScore();
            idOfthirdBestScore = generationMemory.get(0).getId();
        }
    }

    private void log(String step) {

        if (step.equals("start")) {
            System.out.println("\n------------------------");
            System.out.println("------GENRATION " + count + "------");
            System.out.println("------------------------");
        }

        if (step.equals("summary")) {
            System.out.println("\n-----------------------");
            System.out.println("\nBest prey: " + generationMemory.get(0).getId() + " score: " + generationMemory.get(0).getScore());
            System.out.println("\n-----------------------");

            System.out.println("PAST genes to next gen: \ngenerationMemory.getSelectedGenomes().size(): " + generationMemory.getSelectedGenomes().size());

            for (int i = 0; i < generationMemory.getSelectedGenomes().size(); i++) {
                System.out.println(i + " - " + generationMemory.get(i).getId());
            }
            System.out.println("\n-----------------------\n");

            System.out.println("AVARAGE score of THIS GENERATION: " + generationMemory.getAvarageScore());
            System.out.println("AVARAGE score of ALL GENERATION: " + this.avarageScoreOfAllGenerations);
            System.out.println("AVARAGE score of BEST GENERATION: " + this.bestScoreOfALLGenerations + " ( " + this.indexOfBestGeneration + " generation )");
            System.out.println("-----------------------");
            System.out.println("1st prey of ALL GENERATION: " + idOfBestScore + " score: " + bestScore);
            System.out.println("2nd prey of ALL GENERATION: " + idOfSecondBestScore + " score: " + secondBestScore);
            System.out.println("3rd prey of ALL GENERATION: " + idOfthirdBestScore + " score: " + thirdBestScore);
            System.out.println("\n\nlist of all:");
            generationsScoresList.add(generationMemory.getAvarageScore());
            for (int i = 0; i < generationsScoresList.size(); i++) {
                System.out.print("--" + (i + 1) + "--\t" + generationsScoresList.get(i) + "\t");
                if ((i + 1) % 3 == 0) {
                    System.out.println();
                }
            }
            System.out.println("\n-------------------------\n");
            System.out.println("\n...START NEW GENERATION...\n");
        }
    }

    private void writeFileLog() throws IOException {
        String fileContent = "GENERATION " + (count - 1) + "\n";
        for (int i = 0; i < generationMemory.getSize(); i++) {
            fileContent += "Genome nr " + i + " id-" + generationMemory.get(i).getId() + ": ";
            fileContent += generationMemory.get(i).saveToMemory();
            fileContent += "\n";
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("/home/lastshadow/IdeaProjects/gameNeuralNet_LOGS/generation_" + (count - 1) + ".txt"));
        writer.write(fileContent);
        writer.close();

    }
}
