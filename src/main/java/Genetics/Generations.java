package Genetics;

import Game.Organisms.Prey;
import NeuralNetwork.Generation;
import NeuralNetwork.Genome;
import NeuralNetwork.NeuralNetwork;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Generations {
    private static final DecimalFormat DF_2 = new DecimalFormat("#.##");
    private static final int RESET_GENERATION = 50000000;

    private Generation generation;
    private final NeuralNetwork protoplast = new NeuralNetwork(25, 2, 12, 4);
    private double BIAS = 1.0;
    private List<Double> generationsScoresList;
    private List<Double> generationsAverageList;
    private GenerationMemory generationMemory;
    private List<Prey> AI_prey;
    private int generationSize;
    private int count = 1;
    private double avarageScoreOfAllGenerations = 0.0;
    private List<Double> avarageScores = new ArrayList<>();
    private double bestScoreOfALLGenerations = 0.0;
    private int indexOfBestGeneration = 0;
    private double bestScore = 0.0;
    private String idOfBestScore = "";
    private double secondBestScore = 0.0;
    private String idOfSecondBestScore = "";
    private double thirdBestScore = 0.0;
    private String idOfthirdBestScore = "";
    private double mutationRate;
    private int index = 0;
    private double preyMaxAge;
    private boolean firstInGeneration;


    public Generations(List<Prey> AI_prey, int size, double preyMaxAge) {
        this.generationSize = size;
        protoplast.setAllBiases(BIAS);
        this.generation = new Generation(1, generationSize, protoplast);
        this.AI_prey = AI_prey;
        this.generationsScoresList = new ArrayList<>();
        this.generationsAverageList = new ArrayList<>();
        this.mutationRate = 1.1;
        this.preyMaxAge = preyMaxAge;
    }

    public void addFirstGeneration() {
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
        System.out.println("public void addNewGeneration() -> generation.showScores()");
        generation.showScores();
        if (count % RESET_GENERATION == 0) {
            randomGeneration();
        } else {
            int numOfBestSelection = (int) (generationSize / 10);
            geneticsNewGeneration(generation.bestSelection(numOfBestSelection));
        }

        generationMemory.getSelectedGenomes().clear();
        generationMemory = new GenerationMemory(count, 10);
        log("start");
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
        double newMutationRate = (bestScore - avarageScoreOfAllGenerations) / bestScore;
        setMutationRate(newMutationRate);
        generation = new Generation(2, generationSize, ancestors, getGeneticsType(), newMutationRate);
        //generation = new Generation(2, generationSize, ancestors, "top5smallMutation", newMutationRate);

        for (int i = 0; i < generationSize; i++) {
            double xStartPos = 600 + 60 * (Math.random() - 0.5);
            double yStartPos = 400 + 60 * (Math.random() - 0.5);
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
        return array[index];
    }

    public void deathPrey(Prey prey) {
        if (prey.isAlive()) {
            prey.getGenome().setScore(prey.getScore());
            System.out.println(prey.getGenome().getId() + "sc:" + prey.getGenome().getScore());
            generationMemory.add(prey);
            prey.isDead();
            prey.setFirstInGeneration(false);
        }

    }

    private Double calculateAvarageScoreFromXlastGenerations(int numOfGen, int start) {
        Double result = 0.0;
        int lenght = avarageScores.size();
        int startPoint = start;
        int divider = lenght - start;

//        if (numOfGen < lenght) {
//            //startPoint = lenght - numOfGen;
//            startPoint = start;
//            divider = numOfGen;
//        }

        for (int i = startPoint; i < lenght; i++) {
            result += avarageScores.get(i);
        }

        return result / divider;
    }

    private void updateAvaregeScores() {
        avarageScores.add(generationMemory.getAvarageScore());

        if (avarageScoreOfAllGenerations == 0) {
            avarageScoreOfAllGenerations = generationMemory.getAvarageScore();
        } else {
            //avarageScoreOfAllGenerations = (avarageScoreOfAllGenerations * (count - 1) + generationMemory.getAvarageScore()) / (count);

            int precise = RESET_GENERATION / 3;
//            int precise = 40;
//            if (count > 500) {
//                precise = 100;
//            }
//            if (count > 1000) {
//                precise = 200;
//            }

            int startPoint = (int) (Math.floor(count / (double) RESET_GENERATION)) * RESET_GENERATION;
            avarageScoreOfAllGenerations = calculateAvarageScoreFromXlastGenerations(precise, startPoint);
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
            generationsAverageList.add(this.avarageScoreOfAllGenerations);

            for (int i = 0; i < generationsScoresList.size(); i++) {
                System.out.print("\t\t--" + (i + 1) + "--\t\t" + DF_2.format(this.generationsScoresList.get(i)) + "\t\t( " + DF_2.format(this.generationsAverageList.get(i)) + " )\t\t");
                if ((i + 1) % 3 == 0) {
                    System.out.println();
                }

            }
            System.out.println("\n-------------------------\n");
            System.out.println("\n...START NEW GENERATION...\n");
        }

    }

    private void writeFileLog() throws IOException {
        StringBuffer fileContent = new StringBuffer();
        fileContent.append("GENERATION ").append(count - 1).append("\n");
        fileContent.append("\nAVARAGE score of THIS GENERATION: ").append(generationMemory.getAvarageScore());
        fileContent.append("\nAVARAGE score of ALL GENERATION: ").append(this.avarageScoreOfAllGenerations);
        fileContent.append("\nAVARAGE score of BEST GENERATION: ").append(this.bestScoreOfALLGenerations).append(" ( ").append(this.indexOfBestGeneration).append(" generation )");
        fileContent.append("\n--------------------------------------");
        fileContent.append("\n\n1st prey of ALL GENERATION: ").append(idOfBestScore).append(" score: ").append(bestScore);
        fileContent.append("\n2nd prey of ALL GENERATION: ").append(idOfSecondBestScore).append(" score: ").append(secondBestScore);
        fileContent.append("\n3rd prey of ALL GENERATION: ").append(idOfthirdBestScore).append(" score: ").append(thirdBestScore);
        fileContent.append("\n--------------------------------------");

        for (int i = 0; i < generationMemory.getSize(); i++) {
            fileContent.append("\n\nGenome nr ").append(i).append(" id-").append(generationMemory.get(i).getId()).append(": ");
            fileContent.append(generationMemory.get(i).saveToMemory());
            fileContent.append("\n");
        }

//        BufferedWriter writer = new BufferedWriter(new FileWriter("/home/lastshadow/Documents/neuralNetworkTests/logs/generation_" + (count - 1) + ".txt"));
//        writer.write(String.valueOf(fileContent));
//        writer.close();
//        fileContent.setLength(0);
    }

    public List<Double> getGenerationsScoresList() {
        return generationsScoresList;
    }

    public List<Double> getGenerationsAverageList() {
        return generationsAverageList;
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
}
