package NeuralNetwork;

public class OutputAI {

    private double angle;
    private double accelleration;

    public OutputAI(double angle, double accelleration) {
        this.angle = angle;
        this.accelleration = accelleration;
    }

    public double getAngle() {
        return angle;
    }

    public double getAccelleration() {
        return accelleration;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setAccelleration(double accelleration) {
        this.accelleration = accelleration;
    }
}
