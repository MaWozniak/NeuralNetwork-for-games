package neuralnetwork;

public class BlackBox {
    Genome genome;
    double[] input;
    double[] output;

    /*
    The biggest speed factor of the App: for 1*5 and 2*3 it's really fast; optimal is: 5*5, 8*3
     */
    private final int HORIZONTAL_VIEW_POINTS = 5 * 5; // it must be multiple of 5
    private final int VERTICAL_VIEW_POINTS = 8 * 3; // it must be multiple of 3

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

        bytes:
        0 - nothing     1 - hide places     2 - food        3 - prey        4 - predator

         */
        for (int i = 0; i < HORIZONTAL_VIEW_POINTS; ++i) {
            for (int j = 0; j < VERTICAL_VIEW_POINTS; ++j) {
                // LEFT
                if(i > -1 & i < 2*HORIZONTAL_VIEW_POINTS/5) { // 0 - 10
                    // BACK
                    if(j > -1 & j <= VERTICAL_VIEW_POINTS/3) { // 0 - 8
                        if (viewTable[i][j] == 4) {
                            predatorLeftBack = 1.0;
                        }
                    }
                    // FRONT
                    if(j > VERTICAL_VIEW_POINTS/3 & j < VERTICAL_VIEW_POINTS) { // 8 - 24
                        if (viewTable[i][j] == 4) {
                            predatorLeft = 1.0;
                        }
                        if (viewTable[i][j] == 2) {
                            foodLeft = 1.0;
                        }
                        if (viewTable[i][j] == 3) {
                            anotherPreyLeft = 1.0;
                        }
                        if (viewTable[i][j] == 1) {
                            hidePlaceFront = 1.0;
                        }
                    }
                }
                // HORIZONTAL CENTER
                if(i >= 2*HORIZONTAL_VIEW_POINTS/5 & i <= 3*HORIZONTAL_VIEW_POINTS/5) { // 10 - 15
                    // BACK
                    if(j > -1 & j <= VERTICAL_VIEW_POINTS/3) { // 0 - 8
                        if (viewTable[i][j] == 4) {
                            predatorBack = 1.0;
                        }
                    }
                    // FRONT
                    if(j > VERTICAL_VIEW_POINTS/3 & j < VERTICAL_VIEW_POINTS) { // 8 - 24
                        if (viewTable[i][j] == 4) {
                            predatorCenter = 1.0;
                        }
                        if (viewTable[i][j] == 2) {
                            foodFront = 1.0;
                        }
                        if (viewTable[i][j] == 3) {
                            anotherPreyFront = 1.0;
                        }
                    }
                }
                // RIGHT
                if(i > 3*HORIZONTAL_VIEW_POINTS/5 & i < HORIZONTAL_VIEW_POINTS) { // 15 - 25
                    // BACK
                    if(j > -1 & j <= VERTICAL_VIEW_POINTS/3) { // 0 - 8
                        if (viewTable[i][j] == 4) {
                            predatorRightBack = 1.0;
                        }
                    }
                    // FRONT
                    if(j > VERTICAL_VIEW_POINTS/3 & j < VERTICAL_VIEW_POINTS) { // 8 - 24
                        if (viewTable[i][j] == 4) {
                            predatorRight = 1.0;
                        }
                        if (viewTable[i][j] == 2) {
                            foodRight = 1.0;
                        }
                        if (viewTable[i][j] == 3) {
                            anotherPreyRight = 1.0;
                        }
                    }
                }
                // CENTER
                if(i >= 2*HORIZONTAL_VIEW_POINTS/5 & i <= 3*HORIZONTAL_VIEW_POINTS/5) { // 10 - 15

                    if(j >= VERTICAL_VIEW_POINTS/6 & j <= 3*VERTICAL_VIEW_POINTS/6) { // 4 - 12
                        if (viewTable[i][j] == 4) {
                            predatorNear = 1.0;
                        }
                        if (viewTable[i][j] == 2) {
                            foodCenter = 1.0;
                        }
                    }
                }
                // NEAR CENTER & IN POINT
                if(i >= 11*HORIZONTAL_VIEW_POINTS/25 & i <= 13*HORIZONTAL_VIEW_POINTS/25) { // 11 - 13

                    if(j >= 6*VERTICAL_VIEW_POINTS/24 & j <= 10*VERTICAL_VIEW_POINTS/24) { // 6 - 10
                        if (viewTable[i][j] == 2) {
                            foodInPoint = 1.0;
                        }
                        if (viewTable[i][j] == 1) {
                            hidePlaceInPoint = 1.0;
                        }

                    }
                }
            }
        }

        // TODO to jest źle, musze uwzglednic angle
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
        double frontalTransposition = 150.0;
        double sideTransposition = 150.0;

        byte[][] viewTable = new byte[HORIZONTAL_VIEW_POINTS][VERTICAL_VIEW_POINTS];

        double tempSideTransposition = -sideTransposition; // from -1 to 1


        for (int i = 0; i < HORIZONTAL_VIEW_POINTS; ++i) {

            tempSideTransposition += (2 * sideTransposition) / HORIZONTAL_VIEW_POINTS;

            double tempFrontalTransposition = -frontalTransposition/2; //from -1/2 to 1 1/2

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

    public Genome getGenome() {
        return this.genome;
    }
}
