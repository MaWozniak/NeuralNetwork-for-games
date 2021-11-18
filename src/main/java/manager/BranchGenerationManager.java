package manager;

import game.organisms.Prey;
import game.stage.StageManager;

import java.util.LinkedList;
import java.util.List;

public class BranchGenerationManager {

    private final int INITIAL_NUMBER_OF_BRANCHES = 10;
    private int NUMBER_OF_BRANCHES = INITIAL_NUMBER_OF_BRANCHES;
    private final List<Prey> AI_prey;
    private final int size;
    private final double preyMaxAge;
    private final StageManager stageManager;

    private final List<GenerationManager> branches;
    private int actuateCounter = 0;

    public BranchGenerationManager(
            List<Prey> AI_prey,
            int size,
            double preyMaxAge,
            StageManager stageManager
    ) {
        this.AI_prey = AI_prey;
        this.size = size;
        this.preyMaxAge = preyMaxAge;
        this.stageManager = stageManager;

        branches = new LinkedList<>();
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
        actuateBranches();
    }

    private void actuateBranches() {
        int lastBranchIndex = branches.size() - 1;
        int actualGen = branches.get(lastBranchIndex).getCount() - 1;

        int firstStage = 500;
        int nextStageDuration = 200;
        int nextNumberOfBranches = 5;

        // FIRST STEP - BEST 2 FROM 10
        if(actualGen == firstStage & NUMBER_OF_BRANCHES == INITIAL_NUMBER_OF_BRANCHES) {
            NUMBER_OF_BRANCHES = nextNumberOfBranches;
            evaluationBranchesWithChanging();

        }
        // NEXT REGULAR MODIFICATION
        if(actualGen > firstStage & (actualGen - firstStage) % nextStageDuration == 0) {
            evaluationBranchesWithChanging();
        }
    }

    public void addNewGeneration() {
        if(actuateCounter < NUMBER_OF_BRANCHES) {
            this.get().addFirstGeneration();
        } else {
            this.get().addNewGeneration();
        }
        logBucket();
    }

    public String getActualBranchNumber() {
        int actualNumberOfBranch = actuateCounter % NUMBER_OF_BRANCHES + 1;
        return String.valueOf(actualNumberOfBranch);
    }

    private void evaluationBranchesWithChanging() {
        //to refactor
        double maxScore = 0.0;
        double secondScore = 0.0;
        int maxScoreIndex = 0;
        int secondScoreIndex = 0;
        for ( int i = 0; i < branches.size(); i++) {
            double score = branches.get(i).getLastAverageScore();
            if(score >= maxScore) {
                maxScore = score;
                maxScoreIndex = i;
            }
            if(score >= secondScore & score < maxScore) {
                secondScore = score;
                secondScoreIndex = i;
            }
        }
        //end to refactor

        GenerationManager theBestBranch = branches.get(maxScoreIndex);
        GenerationManager theSecondBranch = branches.get(secondScoreIndex);

        for ( int i = branches.size() - 1; i > -1 ; i--) {
            branches.remove(branches.get(i));
        }

        // NEW BRANCHES:
        // 1) best branch
        branches.add(theBestBranch);

        // 2) lower mutation
        GenerationManager newGenMan1 = new GenerationManager(AI_prey, size, preyMaxAge, stageManager);
        newGenMan1.setGeneration(theBestBranch.getGeneration().copy());
        newGenMan1.setLowerMutation();
        newGenMan1.setGenerationMemory(theBestBranch.getGenerationMemory().copy());
        newGenMan1.setGenerationScore(theBestBranch.getGenerationScore().copy());
        branches.add(newGenMan1);

        // 3) shock best branch
        GenerationManager newGenMan2 = new GenerationManager(AI_prey, size, preyMaxAge, stageManager);
        newGenMan2.setGeneration(theBestBranch.getGeneration().copy().makeShock());
        newGenMan2.setGenerationMemory(theBestBranch.getGenerationMemory().copy());
        newGenMan2.setGenerationScore(theBestBranch.getGenerationScore().copy());
        branches.add(newGenMan2);

        // 4) shock second branch
        GenerationManager newGenMan3 = new GenerationManager(AI_prey, size, preyMaxAge, stageManager);
        newGenMan3.setGeneration(theSecondBranch.getGeneration().copy());
        newGenMan3.setGenerationMemory(theSecondBranch.getGenerationMemory().copy());
        newGenMan3.setGenerationScore(theSecondBranch.getGenerationScore().copy());
        branches.add(newGenMan3);

        // 5) random generation
        GenerationManager newGenMan4 = new GenerationManager(AI_prey, size, preyMaxAge, stageManager);
        newGenMan4.setGeneration(theSecondBranch.getGeneration().copy().randomize());
        newGenMan4.setGenerationMemory(theSecondBranch.getGenerationMemory().copy());
        newGenMan4.setGenerationScore(theSecondBranch.getGenerationScore().copy());
        branches.add(newGenMan4);

    }

    public List<List<Double>> getAllGenerationsScoresList() {
        List<List<Double>> result = new LinkedList<>();
        branches.forEach(x -> result.add(x.getGenerationsScoresList()));
        return result;
    }

    public List<List<Double>> getAllGenerationsAverageList() {
        List<List<Double>> result = new LinkedList<>();
        branches.forEach(x -> result.add(x.getGenerationsAverageList()));
        return result;
    }

    private void logBucket() {
        int actualNumberOfBranch = actuateCounter % NUMBER_OF_BRANCHES + 1;
        System.out.println("*************************************");
        System.out.println("______________BUCKET " + actualNumberOfBranch + "_______________");
        System.out.println("*************************************");
    }
}
