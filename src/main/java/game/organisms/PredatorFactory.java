package game.organisms;

public class PredatorFactory {

    private static final boolean ONLY_SIMPLE = true;
    private static final int TIME_FACTOR = 80;

    public Predator createPredator(int generationNum, double xPos, double yPos) {
        if(ONLY_SIMPLE) return new SimplePredator(xPos, yPos);
        else return createPredatorWithParameters(generationNum, xPos, yPos);
    }

    private Predator createPredatorWithParameters(int generationNum, double xPos, double yPos) {
        if (generationNum < TIME_FACTOR) return new GenericPredator(xPos, yPos);
        else if (generationNum < 1 * TIME_FACTOR) return createPredatorWithPercentChance(0.2, xPos, yPos);
        else if (generationNum < 3 * TIME_FACTOR) return createPredatorWithPercentChance(0.4, xPos, yPos);
        else if (generationNum < 5 * TIME_FACTOR) return createPredatorWithPercentChance(0.75, xPos, yPos);
        else return new SimplePredator(xPos, yPos);
    }

    private Predator createPredatorWithPercentChance(double chance, double xPos, double yPos) {
        if (Math.random() > chance) {
            return new SimplePredator(xPos, yPos);
        } else {
            return new GenericPredator(xPos, yPos);
        }
    }
}
