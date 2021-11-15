package neuralnetwork;

public class BlackBox {
    Genome genome;
    double[] input;
    double[] output;

    private final int HORIZONTAL_VIEW_POINTS = 30;
    private final int VERTICAL_VIEW_POINTS = 25;

    public BlackBox(Genome genome) {
        this.genome = genome;
    }

    public double[] getInput() {
        return this.input;
    }

    public double[] getOutput() {
        return this.output;
    }

    public OutputMove move(byte[][] model, double xPos, double yPos, double angle, double speed, double energy, double stamina, double age) {
        double foodLeft = 0.0D;
        double foodFront = 0.0D;
        double foodRight = 0.0D;
        double foodCenter = 0.0D;
        double foodInPoint = 0.0D;

        double anotherPreyLeft = 0.0D;
        double anotherPreyFront = 0.0D;
        double anotherPreyRight = 0.0D;

        double predatorLeft = 0.0D;
        double predatorFront = 0.0D;
        double predatorRight = 0.0D;
        double predatorNear = 0.0D;
        double predatorCenter = 0.0D;
        double predatorLeftBack = 0.0D;
        double predatorBack = 0.0D;
        double predatorRightBack = 0.0D;

        double hidePlaceFront = 0.0D;
        double hidePlaceInPoint = 0.0D;

        double borderFront = 0.0D;
        double borderNearFront = 0.0D;

        double simpleBias = 1.0D;

        byte[][] viewTable = getViewBytesTable(model, xPos, yPos, angle);
        /*
        tu taj rozne warunki
        for (int i = 0; i < viewTable.length; ++i) {
            for (int j = 0; j < viewTable[i].length; ++j) {
                if(i <15 & j >10) {
                    if(viewTable[i][j] == 'P') {
                        prefdatorLeft = 1.0;
                    }
                    if(viewTable[i][j] == 'F') {
                        foodLeft = 1.0;
                    }
                }
            }
        }

        etc.
         */

        /*
        bytes:
        0 - nothing
        1 - hide places
        2 - food
        3 - prey
        4 - predator
         */
        for (int i = 0; i < HORIZONTAL_VIEW_POINTS; ++i) {
            for (int j = 0; j < VERTICAL_VIEW_POINTS; ++j) {
                if (viewTable[i][j] == 4) {
                    predatorCenter = 1.0;
                    predatorFront = 1.0;
                }
                if (viewTable[i][j] == 2) {
                    foodFront = 1.0;
                }
                if (i > HORIZONTAL_VIEW_POINTS / 4
                        & i < 3 * HORIZONTAL_VIEW_POINTS / 4
                        & j > VERTICAL_VIEW_POINTS / 4
                        & j < 3 * VERTICAL_VIEW_POINTS / 4
                ) {
                    if (viewTable[i][j] == 'P') {
                        predatorNear = 1.0;
                        predatorLeftBack = 1.0;
                        predatorRightBack = 1.0;
                        predatorBack = 1.0;
                    }
                    if (viewTable[i][j] == 'F') {
                        foodCenter = 1.0;
                        foodInPoint = 1.0;
                    }
                }
            }
        }

        // to jest źle, musze uwzglednic angle
        if (xPos < 50.0D || xPos > 1200.0D || yPos < 50.0D || yPos > 800.0D) {
            borderFront = 1.0D;
        }

        if (xPos < 1.0D || xPos > 1249.0D || yPos < 1.0D || yPos > 849.0D) {
            borderNearFront = 1.0D;
        }


        this.input = new double[]{
                borderFront,
                borderNearFront,
                foodLeft, foodFront,
                foodRight,
                foodCenter,
                foodInPoint,
                anotherPreyLeft,
                anotherPreyFront,
                anotherPreyRight,
                predatorLeft,
                predatorFront,
                predatorRight,
                predatorNear,
                predatorCenter,
                predatorLeftBack,
                predatorBack,
                predatorRightBack,
                hidePlaceFront,
                hidePlaceInPoint,
                speed,
                10 * energy / 150,
                stamina / 2,
                age / 100,
                simpleBias
        };

        int workingNetwork = this.getGenome().getLearningNetwork();
        this.output = this.getGenome().getNodalNetwork().run(this.input, workingNetwork);

        //interpretation
        boolean[] outputBool = new boolean[4];

        for (int i = 0; i < 4; ++i) {
            outputBool[i] = this.output[i] == 1;
        }

        return new OutputMove(outputBool[0], outputBool[1], outputBool[2], outputBool[3]);
    }

    byte[][] getViewBytesTable(
            byte[][] model,
            double x,
            double y,
            double angle
    ) {
        double frontalTransposition = 300.0;
        double sideTransposition = 250.0;

        byte[][] viewTable = new byte[HORIZONTAL_VIEW_POINTS][VERTICAL_VIEW_POINTS];

        double tempSideTransposition = -sideTransposition; // from -1 to 1


        for (int i = 0; i < HORIZONTAL_VIEW_POINTS; ++i) {

            tempSideTransposition += (2 * sideTransposition) / HORIZONTAL_VIEW_POINTS;

            double tempFrontalTransposition = -frontalTransposition/3; //from -1/3 to 1+2/3

            for (int j = 0; j < VERTICAL_VIEW_POINTS; ++j) {

                tempFrontalTransposition += (2 * frontalTransposition) / VERTICAL_VIEW_POINTS;

                viewTable[i][j] = getCharFromModelView(model, x, y, angle, tempFrontalTransposition, tempSideTransposition);
            }
        }

        return viewTable;
    }

    private byte getCharFromModelView(
            byte[][] model,
            double xPos,
            double yPos,
            double angle,
            double frontalTransposition,
            double sideTransposition
    ) {
        int x = (int) xPos - 6 - (int) (sideTransposition * Math.cos(angle) - (double) ((int) (frontalTransposition * Math.sin(angle))));
        int y = (int) yPos - 6 - (int) (sideTransposition * Math.sin(angle) + (double) ((int) (frontalTransposition * Math.cos(angle))));
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

        return model[x][y];
    }


//    double checkRectangleModelView(char checked, char[][] model, int yStartPoint, int xStartPoint, int yNumPoints, int xNumPoints, double FRONT_TRANSALTE, double SIDE_TRANSALTE, double x, double y, double angle) {
//        double result = 0.0D;
//
//        for (int i = yStartPoint; i < yNumPoints; ++i) {
//            for (int j = xStartPoint; j < xNumPoints; ++j) {
//                if (this.checkModelView(model, x, y, angle, checked, (double) i * (FRONT_TRANSALTE / (double) yNumPoints), (double) j * (SIDE_TRANSALTE / (double) xNumPoints)) == 1.0D) {
//                    result = 1;
//                }
//            }
//        }
//
//        return result;

//    }
//    double checkModelView(char[][] model, double xPos, double yPos, double angle, char checked, double FRONT, double SIDE) {
//        int x = (int) xPos - 6 - (int) (SIDE * Math.cos(angle) - (double) ((int) (FRONT * Math.sin(angle))));
//        int y = (int) yPos - 6 - (int) (SIDE * Math.sin(angle) + (double) ((int) (FRONT * Math.cos(angle))));
//        if (x < 0) {
//            x = 0;
//        }
//
//        if (x > 1250) {
//            x = 1250;
//        }
//
//        if (y < 0) {
//            y = 0;
//        }
//
//        if (y > 850) {
//            y = 850;
//        }
//
//        return this.convCharToDbl(model[x][y], checked);

//    }

//    private double convCharToDbl(char ch, char strike) {
//        double converted = 0;
//        if (ch == strike) {
//            converted = 1;
//        }
//
//        return converted;
//    }

    public Genome getGenome() {
        return this.genome;
    }
}


//        if (this.checkRectangleModelView('F', model, true, 0, 3,
//                10, 10, 280.0D, -200.0D, xPos, yPos, angle) == 1.0D) {
//            foodLeft = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('F', model, false, 1, 0,
//                10, 3, 280.0D, -30.0D, xPos, yPos, angle) == 1.0D) {
//            foodFront = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('F', model, false, 1, 0,
//                10, 3, 280.0D, 30.0D, xPos, yPos, angle) == 1.0D) {
//            foodFront = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('F', model, true, 0, 3,
//                10, 10, 280.0D, 200.0D, xPos, yPos, angle) == 1.0D) {
//            foodRight = 1.0D;
//        }
//        if (this.checkRectangleModelView('F', model, false, -2, 0,
//                3, 2, 12.0D, -12.0D, xPos, yPos, angle) == 1.0D) {
//            foodCenter = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('F', model, false, -2, 0,
//                3, 2, 12.0D, 12.0D, xPos, yPos, angle) == 1.0D) {
//            foodCenter = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('F', model, false, -2, 0,
//                5, 5, 12.0D, -12.0D, xPos, yPos, angle) == 1.0D) {
//            foodInPoint = 1.0D;
//        }
//        if (this.checkRectangleModelView('F', model, false, -2, 0,
//                5, 5, 12.0D, 12.0D, xPos, yPos, angle) == 1.0D) {
//            foodInPoint = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('X', model, true, 0, 2,
//                10, 10, 220.0D, -180.0D, xPos, yPos, angle) == 1.0D) {
//            anotherPreyLeft = 1.0D;
//        }
//
//
//        if (this.checkRectangleModelView('X', model, false, 1, 0,
//                10, 3, 200.0D, -30.0D, xPos, yPos, angle) == 1.0D) {
//            anotherPreyFront = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('X', model, false, 1, 0,
//                10, 3, 200.0D, 30.0D, xPos, yPos, angle) == 1.0D) {
//            anotherPreyFront = 1.0D;
//        }
//
//
//        if (this.checkRectangleModelView('X', model, true, 0, 2,
//                10, 10, 220.0D, 180.0D, xPos, yPos, angle) == 1.0D) {
//            anotherPreyRight = 1.0D;
//        }
//
//        hidePlaceFront = this.checkModelView(model, xPos, yPos, angle, 'M', 160.0D, 0.0D);


//        if (this.checkRectangleModelView('P', model, false, 0, 2,
//                15, 15, 270.0D, -200.0D, xPos, yPos, angle) == 1.0D) {
//            predatorLeft = 1.0D;
//        }
//
//
//        if (this.checkRectangleModelView('P', model, false, 1, 0,
//                16, 5, 290.0D, -30.0D, xPos, yPos, angle) == 1.0D) {
//            predatorFront = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('P', model, false, 1, 0,
//                16, 5, 290.0D, 30.0D, xPos, yPos, angle) == 1.0D) {
//            predatorFront = 1.0D;
//        }
//
//
//        if (this.checkRectangleModelView('P', model, false, 0, 2,
//                15, 15, 270.0D, 200.0D, xPos, yPos, angle) == 1.0D) {
//            predatorRight = 1.0D;
//        }
//
//
//        if (this.checkRectangleModelView('P', model, false, -4, 0,
//                8, 8, 24.0D, -24.0D, xPos, yPos, angle) == 1.0D) {
//            predatorNear = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('P', model, false, -4, 0,
//                8, 8, 24.0D, 24.0D, xPos, yPos, angle) == 1.0D) {
//            predatorNear = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('P', model, false, -2, 0,
//                5, 5, 12.0D, -12.0D, xPos, yPos, angle) == 1.0D) {
//            predatorCenter = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('P', model, false, -2, 0,
//                5, 5, 12.0D, 12.0D, xPos, yPos, angle) == 1.0D) {
//            predatorCenter = 1.0D;
//        }
//        if (this.checkRectangleModelView('P', model, false, 0, 2,
//                8, 8, -70.0D, -80.0D, xPos, yPos, angle) == 1.0D) {
//            predatorLeftBack = 1.0D;
//        }
//
//
//        if (this.checkRectangleModelView('P', model, false, 0, 0,
//                8, 4, -80.0D, -30.0D, xPos, yPos, angle) == 1.0D) {
//            predatorBack = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('P', model, false, 0, 0,
//                8, 4, -80.0D, 30.0D, xPos, yPos, angle) == 1.0D) {
//            predatorBack = 1.0D;
//        }
//
//        if (this.checkRectangleModelView('P', model, false, 0, 2,
//                8, 8, -70.0D, 80.0D, xPos, yPos, angle) == 1.0D) {
//            predatorRightBack = 1.0D;
//        }
//
//
//        if (this.checkRectangleModelView('M', model, false, -2, 0,
//                5, 5, 12.0D, -12.0D, xPos, yPos, angle) == 1.0D) {
//            hidePlaceInPoint = 1.0D;
//        }
//        if (this.checkRectangleModelView('M', model, false, -2, 0,
//                5, 5, 12.0D, 12.0D, xPos, yPos, angle) == 1.0D) {
//            hidePlaceInPoint = 1.0D;
//        }
//
