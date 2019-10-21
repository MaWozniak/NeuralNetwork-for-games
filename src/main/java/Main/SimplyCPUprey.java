package Main;

public class SimplyCPUprey {

    UpdateMovement updateMovement;

    SimplyCPUprey(int updateFramerate) {
        updateMovement = new UpdateMovement(updateFramerate);
        updateMovement.start();
    }

    void thinking(char[][] model, Prey2 prey2) {

        int dirAngle = updateMovement.getDirection();
        double acceleration = updateMovement.getAcceleration();

        radians = (Math.PI / 180) * (prey2.directionAngle);

        //EYES of the main.Prey
        int k = (int) x - 5 + (int) (90.0 * Math.cos(radians));
        int l = (int) y - 5 + (int) (90.0 * Math.sin(radians));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                directionAngle = -directionAngle;
                velocity += 2 * velocity;
            }
        }
        k = ((int) x - 5) + (int) (40.0 * Math.cos(radians) - (int) (40.0 * Math.sin(radians)));
        l = ((int) y - 5) + (int) (40.0 * Math.sin(radians) + (int) (40.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                directionAngle = directionAngle - 30;
                velocity = 2 * velocity;
            }
        }
        k = ((int) x - 5) + (int) (40.0 * Math.cos(radians) + (int) (40.0 * Math.sin(radians)));
        l = ((int) y - 5) + (int) (40.0 * Math.sin(radians) - (int) (40.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                directionAngle = directionAngle + 30;
                velocity = 2 * velocity;
            }
        }
    }
}
