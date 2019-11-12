package Game;

class PreyLogicSimple {

    private UpdateMovement updateMovement;

    PreyLogicSimple(int updateFramerate) {
        updateMovement = new UpdateMovement(updateFramerate);
        updateMovement.start();
    }

    void thinking(char[][] model, Prey prey) {

        prey.up = updateMovement.isUp();
        prey.down = updateMovement.isDown();
        prey.left = updateMovement.isLeft();
        prey.right = updateMovement.isRight();

        //EYES:
        int k = (int) prey.getX() - 5 + (int) (50.0 * Math.sin(prey.angle));
        int l = (int) prey.getY() - 5 - (int) (50.0 * Math.cos(prey.angle));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                prey.left = true;
                prey.up = true;
            }
            if (prey.energy < 100 & model[k][l] == 'F') {
                prey.up = true;
            }
        }

        k = (int) prey.getX() - 5 + (int) (10.0 * Math.sin(prey.angle));
        l = (int) prey.getY() - 5 - (int) (10.0 * Math.cos(prey.angle));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (prey.energy < 130 & model[k][l] == 'F') {
                prey.down = true;
            }
            if (prey.energy > 130 & model[k][l] == 'M') {
                prey.down = true;
            }
        }

        k = ((int) prey.getX() - 5) + (int) (40.0 * Math.cos(prey.angle) + (int) (20.0 * Math.sin(prey.angle)));
        l = ((int) prey.getY() - 5) + (int) (40.0 * Math.sin(prey.angle) - (int) (20.0 * Math.cos(prey.angle)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                prey.left = true;
                prey.up = true;
            }
            if (model[k][l] == 'F') {
                prey.right = true;
            }
        }

        k = ((int) prey.getX() - 5) - (int) (40.0 * Math.cos(prey.angle) - (int) (20.0 * Math.sin(prey.angle)));
        l = ((int) prey.getY() - 5) - (int) (40.0 * Math.sin(prey.angle) + (int) (20.0 * Math.cos(prey.angle)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                prey.right = true;
                prey.up = true;
            }
            if (model[k][l] == 'F') {
                prey.left = true;
            }
        }

        if (prey.energy < 35 & prey.speed == 0) {
            prey.up = true;
        }
    }
}
