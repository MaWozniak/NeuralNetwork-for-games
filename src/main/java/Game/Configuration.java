package Game;

public class Configuration {
    private final int numberOfPrey;
    private final int numberOfPredator;
    private final double preyMaxAge;

    public Configuration(int numberOfPrey, int numberOfPredator, double preyMaxAge) {
        this.numberOfPrey = numberOfPrey;
        this.numberOfPredator = numberOfPredator;
        this.preyMaxAge = preyMaxAge;
    }

    public int getNumberOfPrey() {
        return numberOfPrey;
    }

    public int getNumberOfPredator() {
        return numberOfPredator;
    }

    public double getPreyMaxAge() {
        return preyMaxAge;
    }
}
