package genetics;

import game.organisms.Prey;
import game.stage.StageManager;

import java.util.ArrayList;
import java.util.List;

public class BranchGenerationManager {

    private static final int NUMBER_OF_BRANCHES = 5;

    private final List<GenerationManager> branches;
    private int actuateCounter = 0;

    public BranchGenerationManager(
            List<Prey> AI_prey,
            int size,
            double preyMaxAge,
            StageManager stageManager
    ) {
        branches = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BRANCHES; i++) {
            branches.add(new GenerationManager(AI_prey, size, preyMaxAge, stageManager));
        }

    }

    public GenerationManager get() {
        int actualNumberOfBranch = actuateCounter % NUMBER_OF_BRANCHES;
        return branches.get(actualNumberOfBranch);
    }

    public void actuate() {
        ++actuateCounter;
    }

    public void addNewGeneration() {
        if(actuateCounter < NUMBER_OF_BRANCHES) {
            this.get().addFirstGeneration();
        } else {
            this.get().addNewGeneration();
        }
        logBucket();
    }

    private void logBucket() {
        int actualNumberOfBranch = actuateCounter % NUMBER_OF_BRANCHES + 1;
        System.out.println("*************************************");
        System.out.println("______________BUCKET " + actualNumberOfBranch + "_______________");
        System.out.println("*************************************");
    }

    public String getActualBranchNumber() {
        int actualNumberOfBranch = actuateCounter % NUMBER_OF_BRANCHES + 1;
        return String.valueOf(actualNumberOfBranch);
    }

    public List<List<Double>> getAllGenerationsScoresList() {
        List<List<Double>> result = new ArrayList<>();
        branches.forEach(x -> result.add(x.getGenerationsScoresList()));
        return result;
    }

    public List<List<Double>> getAllGenerationsAverageList() {
        List<List<Double>> result = new ArrayList<>();
        branches.forEach(x -> result.add(x.getGenerationsAverageList()));
        return result;
    }
}
