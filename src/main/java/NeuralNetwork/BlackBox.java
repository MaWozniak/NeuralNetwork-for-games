package NeuralNetwork;

import java.awt.*;

public class BlackBox {

    Genome genome;
    double[] input;
    double[] output;

    public BlackBox() {
    }

    public BlackBox(Genome genome) {
        this.genome = genome;
    }

    public double[] getInput() {
        return input;
    }

    public double[] getOutput() {
        return output;
    }

    public OutputMove move(char[][] model, double xPos, double yPos, double angle, double speed, double energy) {

        // "F" = feed
        // "X" = another prey
        // "P" = predator
        // "M" = hide place

        double foodLeft = 0.0;
        if (checkRectangleModelView('F', model, true, 0, 3, 10, 10,
                280.0, -200.0, xPos, yPos, angle) == 1.0) {
            foodLeft = 1.0;
        }

        double foodFront = 0.0;
        if (checkRectangleModelView('F', model, false, 1, 0, 10, 3,
                280.0, -30.0, xPos, yPos, angle) == 1.0) {
            foodFront = 1.0;
        }
        if (checkRectangleModelView('F', model, false, 1, 0, 10, 3,
                280.0, 30.0, xPos, yPos, angle) == 1.0) {
            foodFront = 1.0;
        }

        double foodRight = 0.0;
        if (checkRectangleModelView('F', model, true, 0, 3, 10, 10,
                280.0, 200.0, xPos, yPos, angle) == 1.0) {
            foodRight = 1.0;
        }

        double foodCenter = 0.0;
        if (checkRectangleModelView('F', model, false, -2, 0, 3, 2,
                12.0, -12.0, xPos, yPos, angle) == 1.0) {
            foodCenter = 1.0;
        }
        if (checkRectangleModelView('F', model, false, -2, 0, 3, 2,
                12.0, 12.0, xPos, yPos, angle) == 1.0) {
            foodCenter = 1.0;
        }

        double foodInPoint = checkModelView(model, xPos, yPos, angle, 'F', 0.0, 0.0);

        double anotherPreyLeft = 0.0;
        if (checkRectangleModelView('X', model, true, 0, 2, 10, 10,
                220.0, -180.0, xPos, yPos, angle) == 1.0) {
            anotherPreyLeft = 1.0;
        }

        double anotherPreyFront = 0.0;
        if (checkRectangleModelView('X', model, false, 1, 0, 10, 3,
                200.0, -30.0, xPos, yPos, angle) == 1.0) {
            anotherPreyFront = 1.0;
        }
        if (checkRectangleModelView('X', model, false, 1, 0, 10, 3,
                200.0, 30.0, xPos, yPos, angle) == 1.0) {
            anotherPreyFront = 1.0;
        }

        double anotherPreyRight = 0.0;
        if (checkRectangleModelView('X', model, true, 0, 2, 10, 10,
                220.0, 180.0, xPos, yPos, angle) == 1.0) {
            anotherPreyRight = 1.0;
        }

        double predatorLeft = 0.0;
        if (checkRectangleModelView('P', model, false, 0, 2, 15, 15,
                270.0, -200.0, xPos, yPos, angle) == 1.0) {
            predatorLeft = 1.0;
        }

        double predatorFront = 0.0;
        if (checkRectangleModelView('P', model, false, 1, 0, 16, 5,
                290.0, -30.0, xPos, yPos, angle) == 1.0) {
            predatorFront = 1.0;
        }
        if (checkRectangleModelView('P', model, false, 1, 0, 16, 5,
                290.0, 30.0, xPos, yPos, angle) == 1.0) {
            predatorFront = 1.0;
        }

        double predatorRight = 0.0;
        if (checkRectangleModelView('P', model, false, 0, 2, 15, 15,
                270.0, 200.0, xPos, yPos, angle) == 1.0) {
            predatorRight = 1.0;
        }

        double predatorNear = 0.0;
        if (checkRectangleModelView('P', model, false, -4, 0, 8, 8,
                24.0, -24.0, xPos, yPos, angle) == 1.0) {
            predatorNear = 1.0;
        }
        if (checkRectangleModelView('P', model, false, -4, 0, 8, 8,
                24.0, 24.0, xPos, yPos, angle) == 1.0) {
            predatorNear = 1.0;
        }

        double predatorCenter = 0.0;
        if (checkRectangleModelView('P', model, false, -2, 0, 5, 5,
                12.0, -12.0, xPos, yPos, angle) == 1.0) {
            predatorCenter = 1.0;
        }
        if (checkRectangleModelView('P', model, false, -2, 0, 5, 5,
                12.0, 12.0, xPos, yPos, angle) == 1.0) {
            predatorCenter = 1.0;
        }

        double predatorLeftBack = 0.0;
        if (checkRectangleModelView('P', model, false, 0, 2, 8, 8,
                -70.0, -80.0, xPos, yPos, angle) == 1.0) {
            predatorLeftBack = 1.0;
        }

        double predatorBack = 0.0;
        if (checkRectangleModelView('P', model, false, 0, 0, 8, 4,
                -80.0, -30.0, xPos, yPos, angle) == 1.0) {
            predatorBack = 1.0;
        }
        if (checkRectangleModelView('P', model, false, 0, 0, 8, 4,
                -80.0, 30.0, xPos, yPos, angle) == 1.0) {
            predatorBack = 1.0;
        }

        double predatorRightBack = 0.0;
        if (checkRectangleModelView('P', model, false, 0, 2, 8, 8,
                -70.0, 80.0, xPos, yPos, angle) == 1.0) {
            predatorRightBack = 1.0;
        }

        double hidePlaceFront = checkModelView(model, xPos, yPos, angle, 'M', 160.0, 0.0);
        double hidePlaceInPoint = checkModelView(model, xPos, yPos, angle, 'M', 0.0, 0.0);

        double borderFront = 0.0;
        if ((xPos < 50) || (xPos > 1200) || (yPos < 50) || (yPos > 800)) {
            borderFront = 1.0;
        }
        double borderNearFront = 0.0;
        if ((xPos < 1) || (xPos > 1249) || (yPos < 1) || (yPos > 849)) {
            borderNearFront = 1.0;
        }

        //add simpleBias
        double simpleBias = 1.0;

        input = new double[]{borderFront, borderNearFront,
                foodLeft, foodFront, foodRight, foodCenter, foodInPoint,
                anotherPreyLeft, anotherPreyFront, anotherPreyRight,
                predatorLeft, predatorFront, predatorRight, predatorNear, predatorCenter, predatorLeftBack, predatorBack, predatorRightBack,
                hidePlaceFront, hidePlaceInPoint,
                speed, 10 * energy / 150, simpleBias};

        output = this.getGenome().getNeuralNetwork().run(input);

        //interpretation:
        boolean[] outputBool = new boolean[4];

        for (int i = 0; i < 4; i++) {
            outputBool[i] = output[i] == 1;
        }
        return new OutputMove(outputBool[0], outputBool[1], outputBool[2], outputBool[3]);

    }

    double checkRectangleModelView(char checked, char[][] model, boolean TRIANGLE, int yStartPoint, int xStartPoint, int yNumPoints, int xNumPoints,
                                   double FRONT_TRANSALTE, double SIDE_TRANSALTE, double x, double y, double angle) {
        double result = 0.0;
        int triangle = 0;
        for (int i = yStartPoint; i < yNumPoints; i++) {
            if (TRIANGLE) {
                triangle = i;
            }
            for (int j = xStartPoint; j < xNumPoints - triangle; j++) {

                if (checkModelView(model, x, y, angle, checked, i * (FRONT_TRANSALTE / yNumPoints), j * (SIDE_TRANSALTE / xNumPoints)) == 1.0) {
                    result = 1.0;
                }
            }
        }
        return result;
    }

    double checkModelView(char[][] model, double xPos, double yPos, double angle, char checked, double FRONT, double SIDE) {

        int x = ((int) xPos - 6) - (int) (SIDE * Math.cos(angle) - (int) (FRONT * Math.sin(angle)));
        int y = ((int) yPos - 6) - (int) (SIDE * Math.sin(angle) + (int) (FRONT * Math.cos(angle)));

        if (x < 0) {
            x = 0;
        }
        if (x > 1250) {
            x = 1250;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > 850) {
            y = 850;
        }

        return convCharToDbl(model[x][y], checked);

    }

    private double convCharToDbl(char ch, char strike) {

        double converted = 0.0;
        if (ch == strike) {
            converted = 1.0;
        }

        return converted;
    }

    public Genome getGenome() {
        return this.genome;
    }
}
