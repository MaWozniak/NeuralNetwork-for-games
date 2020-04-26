package Game;

class PredatorLogicSimple {
    private UpdateMovement updateMovement;

    PredatorLogicSimple(int updateFramerate) {
        updateMovement = new UpdateMovement(updateFramerate);
        updateMovement.start();
    }

    void thinking(char[][] model, Predator predator) {
        predator.up = updateMovement.isUp();
        predator.down = updateMovement.isDown();
        predator.left = updateMovement.isLeft();
        predator.right = updateMovement.isRight();
        //EYES of the main.Predator
        int k = ((int) predator.getX() - 5) + (int) (90.0 * Math.sin(predator.angle));
        int l = ((int) predator.getY() - 5) - (int) (90.0 * Math.cos(predator.angle));
        if (k < 170 || l < 0 || k > 1020 || l > 850) {
            predator.right = true;
        }
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.up = true;
            }
        }

        k = ((int) predator.getX() - 5) + (int) (55.0 * Math.sin(predator.angle));
        l = ((int) predator.getY() - 5) - (int) (55.0 * Math.cos(predator.angle));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.up = true;
            }
        }

        k = ((int) predator.getX() - 5) + (int) (50.0 * Math.cos(predator.angle) + (int) (70.0 * Math.sin(predator.angle)));
        l = ((int) predator.getY() - 5) + (int) (50.0 * Math.sin(predator.angle) - (int) (70.0 * Math.cos(predator.angle)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.right = true;
                predator.up = true;
            }
        }

        k = ((int) predator.getX() - 5) - (int) (50.0 * Math.cos(predator.angle) - (int) (70.0 * Math.sin(predator.angle)));
        l = ((int) predator.getY() - 5) - (int) (50.0 * Math.sin(predator.angle) + (int) (70.0 * Math.cos(predator.angle)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.left = true;
                predator.up = true;
            }
        }
        k = ((int) predator.getX() - 5) + (int) (80.0 * Math.cos(predator.angle) + (int) (35.0 * Math.sin(predator.angle)));
        l = ((int) predator.getY() - 5) + (int) (80.0 * Math.sin(predator.angle) - (int) (35.0 * Math.cos(predator.angle)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.right = true;
                predator.up = true;
            }
        }

        k = ((int) predator.getX() - 5) - (int) (80.0 * Math.cos(predator.angle) - (int) (35.0 * Math.sin(predator.angle)));
        l = ((int) predator.getY() - 5) - (int) (80.0 * Math.sin(predator.angle) + (int) (35.0 * Math.cos(predator.angle)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.left = true;
                predator.up = true;
            }
        }

        k = ((int) predator.getX() - 5) + (int) (35.0 * Math.cos(predator.angle) + (int) (30.0 * Math.sin(predator.angle)));
        l = ((int) predator.getY() - 5) + (int) (35.0 * Math.sin(predator.angle) - (int) (30.0 * Math.cos(predator.angle)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.right = true;
                predator.up = true;
            }
        }

        k = ((int) predator.getX() - 5) - (int) (35.0 * Math.cos(predator.angle) - (int) (30.0 * Math.sin(predator.angle)));
        l = ((int) predator.getY() - 5) - (int) (35.0 * Math.sin(predator.angle) + (int) (30.0 * Math.cos(predator.angle)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'X') {
                predator.left = true;
                predator.up = true;
            }
        }

        if (predator.energy < 45) {
            predator.up = true;
        }
    }
}
