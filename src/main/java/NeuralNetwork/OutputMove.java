package NeuralNetwork;

public class OutputMove {

    private int angle;
    private double accelleration;

    OutputMove(int angle, double accelleration) {
        this.angle = angle;
        this.accelleration = accelleration;
    }

    public int getAngle() {
        return angle;
    }

    public double getAccelleration() {
        return accelleration;
    }

}
