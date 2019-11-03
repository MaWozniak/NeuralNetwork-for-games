package Game;

import java.util.List;

class Generations {

    private List<PreyAI> AI_prey;
    private int generationSize;
    private int count = 0;

    Generations(List<PreyAI> AI_prey, int size) {
        this.AI_prey = AI_prey;
        this.generationSize = size;
    }

    void addNewGeneration() {

        if (count > 0) {
            System.out.println("\n-----------------------");
            System.out.println("\nBest prey: id prey + score: xxx");
            System.out.println("\n-----------------------");
            System.out.println("AVARAGE score of THIS GENERATION: xxx");
            System.out.println("AVARAGE score of ALL GENERATION: xxx");
            System.out.println("AVARAGE score of BEST GENERATION: xxx ( X generation )");
            System.out.println("-----------------------");
            System.out.println("1st prey of ALL GENERATION: id prey + score: xxx");
            System.out.println("2nd prey of ALL GENERATION: id prey + score: xxx");
            System.out.println("3rd prey of ALL GENERATION: id prey + score: xxx");
            System.out.println("-----------------------\n");
            System.out.println("\n...START NEW GENERATION...\n");
        }
        count++;

        for (int i = 0; i < generationSize; i++) {

            double xStartPos = 1150 * Math.random();
            double yStartPos = 750 * Math.random();
            PreyAI newPreyAI = new PreyAI(xStartPos, yStartPos);
            newPreyAI.setId(i, count);
            AI_prey.add(newPreyAI);
        }

        log();
    }

    private void log() {
        System.out.println("\n------------------------");
        System.out.println("------GENRATION " + count + "------");
        System.out.println("------------------------");
//        for (PreyAI preyAI : AI_prey) {
//            System.out.println(preyAI.getId() + " = " + preyAI.showGenome());
//        }
    }
}
