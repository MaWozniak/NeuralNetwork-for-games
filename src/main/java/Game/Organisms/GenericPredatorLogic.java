package Game.Organisms;

class GenericPredatorLogic {

    void thinking(char[][] model, GenericPredator predator) {
        predator.left = false;
        predator.down = false;
        predator.up = false;
        predator.right = true;

        if (predator.speed < predator.getMaxSpeed() / 4) {
            predator.up = true;
        }

    }

}
