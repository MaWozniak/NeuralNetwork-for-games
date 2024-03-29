package manager;

import game.stage.StageManager;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class GenerationScore {
    private static final DecimalFormat DF_2 = new DecimalFormat("#.#");
    private List<Double> generationsScoresList;
    private List<Double> generationsAverageList;
    private double averageScoreOfAllGenerations = 0.0;
    private final List<Double> averageScores = new LinkedList<>();
    private double bestScoreOfALLGenerations = 0.0;
    private int indexOfBestGeneration = 0;
    private double bestScore = 0.0;
    private String idOfBestScore = "";
    private double secondBestScore = 0.0;
    private String idOfSecondBestScore = "";
    private double thirdBestScore = 0.0;
    private String idOfThirdBestScore = "";

    GenerationScore() {
        this.generationsScoresList = new LinkedList<>();
        this.generationsAverageList = new LinkedList<>();
    }

    double calculateAverageScoreFrom_N_lastGenerations() {
        int resetGen = 50;
        int length = averageScores.size();

        List<Double> last_N_scoresArray = new LinkedList<>();

        if (length <= resetGen) {
            last_N_scoresArray.addAll(averageScores);
            double sumOfAll = last_N_scoresArray.stream().mapToDouble(Double::doubleValue).sum();
            return sumOfAll / length;
        } else {
            for (int i = length - resetGen; i < length; i++) {
                last_N_scoresArray.add(averageScores.get(i));
            }
            double sumOfLastN = last_N_scoresArray.stream().mapToDouble(Double::doubleValue).sum();
            return sumOfLastN / resetGen;
        }

    }

    void addNewGeneration(
            int count,
            StageManager stageManager,
            GenerationMemory generationMemory
    ) {
        this.updateAverageScores(generationMemory);
        this.updateBestIndividualScores(generationMemory);
        this.log("summary", count, stageManager, generationMemory);
    }

    void updateAverageScores(GenerationMemory generationMemory) {
        averageScores.add(generationMemory.getAverageScore());

        if (averageScoreOfAllGenerations == 0) {
            averageScoreOfAllGenerations = generationMemory.getAverageScore();
        } else {
            averageScoreOfAllGenerations = calculateAverageScoreFrom_N_lastGenerations();
        }

        if (generationMemory.getAverageScore() > this.bestScoreOfALLGenerations) {
            bestScoreOfALLGenerations = generationMemory.getAverageScore();
            indexOfBestGeneration = generationMemory.getId();
        }

    }

    void updateBestIndividualScores(GenerationMemory generationMemory) {
        if (generationMemory.get(0).getScore() >= bestScore) {
            thirdBestScore = secondBestScore;
            idOfThirdBestScore = idOfSecondBestScore;
            secondBestScore = bestScore;
            idOfSecondBestScore = idOfBestScore;
            bestScore = generationMemory.get(0).getScore();
            idOfBestScore = generationMemory.get(0).getId();
        } else if (generationMemory.get(0).getScore() >= secondBestScore) {
            thirdBestScore = secondBestScore;
            idOfThirdBestScore = idOfSecondBestScore;
            secondBestScore = generationMemory.get(0).getScore();
            idOfSecondBestScore = generationMemory.get(0).getId();
        } else if (generationMemory.get(0).getScore() >= thirdBestScore) {
            thirdBestScore = generationMemory.get(0).getScore();
            idOfThirdBestScore = generationMemory.get(0).getId();
        }

    }

    void log(
            String step,
            int count,
            StageManager stageManager,
            GenerationMemory generationMemory
    ) {
        if (step.equals("start")) {
            System.out.println("\n------------------------");
            System.out.println("------GENRATION " + count + "------");
            System.out.println("------------------------");
            System.out.println("------Stage " + stageManager.getStage(count - 1).getName() + "------");
            System.out.println("------------------------");
        }

        if (step.equals("summary")) {
            System.out.println("\n-----------------------");
            System.out.println("\nBest prey: " + generationMemory.get(0).getId() + " score: " + generationMemory.get(0).getScore());
            System.out.println("\n-----------------------");
            System.out.println("PAST genes to next gen: \ngenerationMemory.getSelectedGenomes().size(): " + generationMemory.getSelectedGenomes().size());
            System.out.println("\n-----------------------\n");
            System.out.println("AVARAGE score of THIS GENERATION: " + generationMemory.getAverageScore());
            System.out.println("AVARAGE score of ALL GENERATION: " + this.averageScoreOfAllGenerations);
            System.out.println("AVARAGE score of BEST GENERATION: " + this.bestScoreOfALLGenerations + " ( " + this.indexOfBestGeneration + " generation )");
            System.out.println("-----------------------");
            System.out.println("1st prey of ALL GENERATION: " + idOfBestScore + " score: " + bestScore);
            System.out.println("2nd prey of ALL GENERATION: " + idOfSecondBestScore + " score: " + secondBestScore);
            System.out.println("3rd prey of ALL GENERATION: " + idOfThirdBestScore + " score: " + thirdBestScore);
            System.out.println("\n\nlist of all:");
            generationsScoresList.add(generationMemory.getAverageScore());
            generationsAverageList.add(this.averageScoreOfAllGenerations);

            boolean showListOfEveryScore = false;
            if (showListOfEveryScore) {
                for (int i = 0; i < generationsScoresList.size(); i++) {
                    System.out.print(
                            "--" + (i + 1)
                                    + "--\t"
                                    + DF_2.format(this.generationsScoresList.get(i))
                                    + "\t( "
                                    + DF_2.format(this.generationsAverageList.get(i))
                                    + " )\t\t"
                    );
                    if ((i + 1) % 3 == 0) {
                        System.out.println();
                    }

                }
            }

            System.out.println("\n-------------------------\n");
            System.out.println("\n...START NEW GENERATION...\n");
        }

    }

    StringBuilder writeFileLog(int count, GenerationMemory generationMemory) {
        StringBuilder fileContent = new StringBuilder();
        fileContent.append("GENERATION ").append(count - 1).append("\n");
        fileContent.append("\nAVARAGE score of THIS GENERATION: ").append(generationMemory.getAverageScore());
        fileContent.append("\nAVARAGE score of ALL GENERATION: ").append(this.averageScoreOfAllGenerations);
        fileContent.append("\nAVARAGE score of BEST GENERATION: ").append(this.bestScoreOfALLGenerations).append(" ( ").append(this.indexOfBestGeneration).append(" generation )");
        fileContent.append("\n--------------------------------------");
        fileContent.append("\n\n1st prey of ALL GENERATION: ").append(idOfBestScore).append(" score: ").append(bestScore);
        fileContent.append("\n2nd prey of ALL GENERATION: ").append(idOfSecondBestScore).append(" score: ").append(secondBestScore);
        fileContent.append("\n3rd prey of ALL GENERATION: ").append(idOfThirdBestScore).append(" score: ").append(thirdBestScore);
        fileContent.append("\n--------------------------------------");

        final boolean WRITE_ONLY_THE_BEST_GENOME = true;

        if (WRITE_ONLY_THE_BEST_GENOME) {
            int bestScoreIndex = 0;
            double bestScore = 0.0;
            for (int i = 0; i < generationMemory.getSize(); i++) {
                if (generationMemory.get(i).getScore() >= bestScore) {
                    bestScoreIndex = i;
                    bestScore = generationMemory.get(i).getScore();
                }
            }
            fileContent.append("\n\nBEST SCORE IS Genome nr ")
                    .append(bestScoreIndex)
                    .append(" id-")
                    .append(generationMemory.get(bestScoreIndex).getId()).append(": ")
                    .append(generationMemory.get(bestScoreIndex).saveToMemory())
                    .append("\n");
        } else {
            for (int i = 0; i < generationMemory.getSize(); i++) {
                fileContent.append("\n\nGenome nr ").append(i).append(" id-").append(generationMemory.get(i).getId()).append(": ");
                fileContent.append(generationMemory.get(i).saveToMemory());
                fileContent.append("\n");
            }
        }
        return fileContent;
    }

    public List<Double> getGenerationsScoresList() {
        return generationsScoresList;
    }

    public List<Double> getGenerationsAverageList() {
        return generationsAverageList;
    }

    public double getNewMutationRate() {
        return (bestScore - averageScoreOfAllGenerations) / bestScore;
    }

    public void setGenerationsScoresList(List<Double> generationsScoresList) {
        this.generationsScoresList = generationsScoresList;
    }

    public void setGenerationsAverageList(List<Double> generationsAverageList) {
        this.generationsAverageList = generationsAverageList;
    }

    public GenerationScore copy() {
        GenerationScore genScore = new GenerationScore();
        genScore.setGenerationsScoresList(new LinkedList<>(this.generationsScoresList));
        genScore.setGenerationsAverageList(new LinkedList<>(this.generationsAverageList));
        return genScore;
    }

}
