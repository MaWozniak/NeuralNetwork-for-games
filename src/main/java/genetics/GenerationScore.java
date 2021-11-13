package genetics;

import game.stage.StageManager;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class GenerationScore {
    private static final DecimalFormat DF_2 = new DecimalFormat("#.#");

    private final List<Double> generationsScoresList;
    private final List<Double> generationsAverageList;
    private double avarageScoreOfAllGenerations = 0.0;
    private final List<Double> avarageScores = new LinkedList<>();
    private double bestScoreOfALLGenerations = 0.0;
    private int indexOfBestGeneration = 0;
    private double bestScore = 0.0;
    private String idOfBestScore = "";
    private double secondBestScore = 0.0;
    private String idOfSecondBestScore = "";
    private double thirdBestScore = 0.0;
    private String idOfthirdBestScore = "";

    GenerationScore() {
        this.generationsScoresList = new LinkedList<>();
        this.generationsAverageList = new LinkedList<>();
    }

    Double calculateAvarageScoreFromXlastGenerations(int start) {
        Double result = 0.0;
        int lenght = avarageScores.size();
        int divider = lenght - start;

        for (int i = start; i < lenght; i++) {
            result += avarageScores.get(i);
        }

        return result / divider;
    }

    void addNewGeneration(
            int count,
            StageManager stageManager,
            GenerationMemory generationMemory
    ) {
        this.updateAverageScores(count, generationMemory);
        this.updateBestIndividualScores(generationMemory);
        this.log("summary", count, stageManager, generationMemory);
        this.writeFileLog(count, generationMemory);
    }

    void updateAverageScores(int count, GenerationMemory generationMemory) {
        avarageScores.add(generationMemory.getAvarageScore());

        if (avarageScoreOfAllGenerations == 0) {
            avarageScoreOfAllGenerations = generationMemory.getAvarageScore();
        } else {
            int startPoint = (int) (Math.floor(count / (double) 50)) * 50;
            avarageScoreOfAllGenerations = calculateAvarageScoreFromXlastGenerations(startPoint);
        }

        if (generationMemory.getAvarageScore() > this.bestScoreOfALLGenerations) {
            bestScoreOfALLGenerations = generationMemory.getAvarageScore();
            indexOfBestGeneration = generationMemory.getId();
        }

    }

    void updateBestIndividualScores(GenerationMemory generationMemory) {
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
                System.out.print("--" + (i + 1) + "--\t" + DF_2.format(this.generationsScoresList.get(i)) + "\t( " + DF_2.format(this.generationsAverageList.get(i)) + " )\t\t");
                if ((i + 1) % 3 == 0) {
                    System.out.println();
                }

            }
            System.out.println("\n-------------------------\n");
            System.out.println("\n...START NEW GENERATION...\n");
        }

    }

    void writeFileLog(int count, GenerationMemory generationMemory) {
        StringBuilder fileContent = new StringBuilder();
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
    }

    public List<Double> getGenerationsScoresList() {
        return generationsScoresList;
    }

    public List<Double> getGenerationsAverageList() {
        return generationsAverageList;
    }

    public double getNewMutationRate() {
        return (bestScore - avarageScoreOfAllGenerations) / bestScore;
    }
}
