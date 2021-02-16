package game.organisms;

class SimplePredatorLogic {
    private final RandomMovement randomMovement;

    SimplePredatorLogic() {
        randomMovement = new RandomMovement();
        randomMovement.start();
    }

    void thinking(char[][] model, SimplePredator predator) {
        predator.left = false;
        predator.down = false;
        predator.up = false;
        predator.right = false;
        predator.setTestingIfSee(false);

        if (predator.speed < predator.getMaxSpeed() / 4) {
            predator.up = true;
        }
        if (Math.random() > 0.6) {
            randomMovement(predator);
        }
        if (seeBorder(predator)) {
            predator.left = true;
        }
        //FAR UPFRONT - DO NOT TURN
        for (int i = 60; i < 500; i += 2) {
            for (int j = -25; j < 25; j += 2) {
                if (seePreyIn(i, j, model, predator)) {
                    predator.right = false;
                    predator.left = false;
                    //predator.setTestingIfSee(true);
                }
            }
        }
        //NEAR UPFRONT - RUSH
        for (int i = 0; i < 70; i += 2) {
            for (int j = -10; j < 10; j += 2) {
                if (seePreyIn(i, j, model, predator)) {
                    predator.up = true;
                    //predator.setTestingIfSee(true);
                }
            }
        }
        //NEAR RIGHT/LEFT CORRECTION
        for (int i = 0; i < 65; i += 2) {

            for (int j = 10; j < 25; j += 2) {
                if (seePreyIn(i, j, model, predator)) {
                    //predator.left = true;
                }
                if (seePreyIn(i, -j, model, predator)) {
                    //predator.right = true;
                }
            }
        }
        if (seeHidePlace(model, predator)) {
            predator.right = true;
        }

        lowEnergyRush(predator);
    }

    private void randomMovement(SimplePredator predator) {
        predator.left = randomMovement.isLeft();
        predator.right = randomMovement.isRight();
    }

    private void lowEnergyRush(SimplePredator predator) {
        if (predator.energy < 45) {
            predator.up = true;
        }
    }

    private boolean seePreyIn(double frontDistance, double sideDistance, char[][] model, SimplePredator predator) {
        int x = ((int) predator.getX() - 5) + (int) (frontDistance * Math.sin(predator.angle) + (int) (sideDistance * Math.cos(predator.angle)));
        int y = ((int) predator.getY() - 5) - (int) (frontDistance * Math.cos(predator.angle) + (int) (sideDistance * Math.sin(predator.angle)));

        if (x > 0 && y > 0 && x < 1250 && y < 850) {
            return model[x][y] == 'X';
        } else {
            return false;
        }
    }

    private boolean seeBorder(SimplePredator predator) {
        double frontDistance = 185.0;
        double sideDistance = 0.0;
        int x = ((int) predator.getX() - 5) + (int) (frontDistance * Math.sin(predator.angle) + (int) (sideDistance * Math.cos(predator.angle)));
        int y = ((int) predator.getY() - 5) - (int) (frontDistance * Math.cos(predator.angle) + (int) (sideDistance * Math.sin(predator.angle)));
        boolean result = false;
        if (x < 0 || y < 0 || x > 1220 || y > 820) {
            result = true;
        }

        return result;
    }

    private boolean seeHidePlace(char[][] model, SimplePredator predator) {
        double frontDistance = 20.0;
        double sideDistance = 0.0;
        int x = ((int) predator.getX() - 5) + (int) (frontDistance * Math.sin(predator.angle) + (int) (sideDistance * Math.cos(predator.angle)));
        int y = ((int) predator.getY() - 5) - (int) (frontDistance * Math.cos(predator.angle) + (int) (sideDistance * Math.sin(predator.angle)));

        if (x > 0 && y > 0 && x < 1250 && y < 850) {
            return model[x][y] == 'M';
        } else {
            return false;
        }
    }

}
