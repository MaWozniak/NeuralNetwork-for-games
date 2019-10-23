package Main;

class SimplyCPUprey {

    private UpdateMovement updateMovement;

    SimplyCPUprey(int updateFramerate) {
        updateMovement = new UpdateMovement(updateFramerate);
        updateMovement.start();
    }

    void thinking(char[][] model, Prey2 prey2) {

        //should be Get i Set
        prey2.directionAngle = updateMovement.getDirection();
        prey2.acceleration = updateMovement.getAcceleration();

        double radians = (Math.PI / 180) * (prey2.directionAngle);

        //EYES of the main.Prey
        int k = (int) prey2.getX() - 5 + (int) (90.0 * Math.cos(radians));
        int l = (int) prey2.getY() - 5 + (int) (90.0 * Math.sin(radians));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                prey2.directionAngle = -prey2.directionAngle;
                prey2.velocity += 2 * prey2.velocity;
            }
        }
        k = ((int) prey2.getX() - 5) + (int) (40.0 * Math.cos(radians) - (int) (40.0 * Math.sin(radians)));
        l = ((int) prey2.getY() - 5) + (int) (40.0 * Math.sin(radians) + (int) (40.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                prey2.directionAngle = prey2.directionAngle - 30;
                prey2.velocity = 2 * prey2.velocity;
            }
        }
        k = ((int) prey2.getX() - 5) + (int) (40.0 * Math.cos(radians) + (int) (40.0 * Math.sin(radians)));
        l = ((int) prey2.getY() - 5) + (int) (40.0 * Math.sin(radians) - (int) (40.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                prey2.directionAngle = prey2.directionAngle + 30;
                prey2.velocity = 2 * prey2.velocity;
            }
        }
    }
}
