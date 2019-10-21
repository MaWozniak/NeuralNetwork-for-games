package NeuralNetwork;

public class OutputAI {

    private double angle;
    private double accelleration;

    OutputAI(double angle, double accelleration) {
        this.angle = angle;
        this.accelleration = accelleration;
    }

    public double getAngle() {
        return angle;
    }

    public double getAccelleration() {
        return accelleration;
    }

}
