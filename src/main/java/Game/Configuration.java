package Game;

public class Configuration {
    private final int numberOfPrey;
    private final double preyMaxAge;

    public Configuration(int numberOfPrey, double preyMaxAge) {
        this.numberOfPrey = numberOfPrey;
        this.preyMaxAge = preyMaxAge;
    }

    public int getNumberOfPrey() {
        return numberOfPrey;
    }

    public double getPreyMaxAge() {
        return preyMaxAge;
    }
}
