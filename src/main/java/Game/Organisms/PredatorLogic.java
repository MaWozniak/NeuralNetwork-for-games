package Game.Organisms;

class PredatorLogic {
    private final RandomMovement randomMovement;

    PredatorLogic() {
        randomMovement = new RandomMovement();
        randomMovement.start();
    }

    void thinking(char[][] model, Predator predator) {
        randomMovement(predator);
        seekingPrey(predator, model);
        lowEnergyRush(predator);

    }

    private void randomMovement(Predator predator) {
        predator.up = randomMovement.isUp();
        predator.down = randomMovement.isDown();
        predator.left = randomMovement.isLeft();
        predator.right = randomMovement.isRight();
    }

    private void lowEnergyRush(Predator predator) {
        if (predator.energy < 45) {
            predator.up = true;
        }
    }

    private boolean seePreyIn(double frontDistance, double sideDistance, char[][] model, Predator predator) {
        int x = ((int) predator.getX() - 5) + (int) (frontDistance * Math.sin(predator.angle) + (int) (sideDistance * Math.cos(predator.angle)));
        int y = ((int) predator.getY() - 5) - (int) (frontDistance * Math.cos(predator.angle) + (int) (sideDistance * Math.sin(predator.angle)));

        if (x > 0 && y > 0 && x < 1250 && y < 850) {
            return model[x][y] == 'X';
        } else {
            return false;
        }
    }

    private void seekingPrey(Predator predator, char[][] model) {
        if ((seePreyIn(55, 0, model, predator)) ||
                (seePreyIn(90, 0, model, predator))) {

            predator.up = true;

        } else if ((seePreyIn(25, 35, model, predator)) ||
                (seePreyIn(35, 50, model, predator)) ||
                (seePreyIn(70, 80, model, predator))) {

            predator.up = true;
            predator.right = true;

        } else if ((seePreyIn(25, -35, model, predator)) ||
                (seePreyIn(35, -50, model, predator)) ||
                (seePreyIn(70, -80, model, predator))) {

            predator.up = true;
            predator.left = true;
        }
    }
}
