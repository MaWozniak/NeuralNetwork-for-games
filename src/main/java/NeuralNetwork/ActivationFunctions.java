package NeuralNetwork;

class ActivationFunctions {

    private double output = 0.0;

    double run(String name, double input) {

        if (name.equals("STEP")) {

            if (input >= 0) {
                output = 1;
            } else {
                output = 0;
            }
        }

        if (name.equals("SIGN")) {

            if (input >= 0) {
                output = 1;
            } else {
                output = -1;
            }
        }

        return output;
    }
}
