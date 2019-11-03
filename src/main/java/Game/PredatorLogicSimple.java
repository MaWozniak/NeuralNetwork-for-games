package Game;

class PredatorLogicSimple {

    private UpdateMovement updateMovement;

    PredatorLogicSimple(int updateFramerate) {
        updateMovement = new UpdateMovement(updateFramerate);
        updateMovement.start();
    }

    void thinking(char[][] model, Predator predator) {

        int updateDirection = updateMovement.getDirection();
        double acceleration = updateMovement.getAcceleration();

        predator.directionAngle += updateDirection;
        predator.velocity += acceleration;
        predator.radians = (Math.PI / 180) * (predator.directionAngle);

        double radians = (Math.PI / 180) * (predator.directionAngle);

        //EYES of the main.Predator
        int k = ((int) predator.getX() - 5) + (int) (90.0 * Math.cos(radians));
        int l = ((int) predator.getY() - 5) + (int) (90.0 * Math.sin(radians));

        //////////////////////////////////////
        // this change  direction instantly when predator see "out of borders" - to fix
        if (k < 170 || l < 0 || k > 1020 || l > 850) {
            predator.directionAngle -= 180;
        }
        //////////////////////////////////////
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                // directionAngle = 0;
                predator.velocity = 1.2 * predator.velocity;
            }
        }
        k = ((int) predator.getX() - 5) + (int) (50.0 * Math.cos(radians) - (int) (70.0 * Math.sin(radians)));
        l = ((int) predator.getY() - 5) + (int) (50.0 * Math.sin(radians) + (int) (70.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.directionAngle = predator.directionAngle + 10;
                predator.velocity = 1.2 * predator.velocity;
            }
        }
        k = ((int) predator.getX() - 5) + (int) (50.0 * Math.cos(radians) + (int) (70.0 * Math.sin(radians)));
        l = ((int) predator.getY() - 5) + (int) (50.0 * Math.sin(radians) - (int) (70.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.directionAngle = predator.directionAngle + 10;
                predator.velocity = 1.2 * predator.velocity;
            }
        }
    }
}
