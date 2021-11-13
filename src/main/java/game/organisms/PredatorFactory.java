package game.organisms;

public class PredatorFactory {

    public Predator createPredator(int generationNum, double xPos, double yPos) {
//        if (generationNum < 300) {
//            return new GenericPredator(xPos, yPos);
//        } else if (generationNum < 600) {
//            return createPredatorWithPercentChance(0.2, xPos, yPos);
//        } else if (generationNum < 1000) {
//            return createPredatorWithPercentChance(0.4, xPos, yPos);
//        } else if (generationNum < 1500) {
//            return createPredatorWithPercentChance(0.75, xPos, yPos);
//        } else {
//            return new SimplePredator(xPos, yPos);
//        }
        return new SimplePredator(xPos, yPos);
    }

    private Predator createPredatorWithPercentChance(double chance, double xPos, double yPos) {
        if (Math.random() > chance) {
            return new SimplePredator(xPos, yPos);
        } else {
            return new GenericPredator(xPos, yPos);
        }
    }
}
