package Main;

class SimplyCPUprey {

    private UpdateMovement updateMovement;

    SimplyCPUprey(int updateFramerate) {
        updateMovement = new UpdateMovement(updateFramerate);
        updateMovement.start();
    }

    void thinking(char[][] model, Prey prey) {

        //should be Get i Set
        int updateDirection = updateMovement.getDirection();
        double acceleration = updateMovement.getAcceleration();

        prey.directionAngle += updateDirection;
        prey.velocity += acceleration;
        prey.radians = (Math.PI / 180) * (prey.directionAngle);

        double radians = (Math.PI / 180) * (prey.directionAngle);

        //EYES of the main.Prey
        int k = (int) prey.getX() - 5 + (int) (90.0 * Math.cos(radians));
        int l = (int) prey.getY() - 5 + (int) (90.0 * Math.sin(radians));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                prey.directionAngle = -prey.directionAngle;
                prey.velocity += 2 * prey.velocity;
            }
        }
        k = ((int) prey.getX() - 5) + (int) (40.0 * Math.cos(radians) - (int) (40.0 * Math.sin(radians)));
        l = ((int) prey.getY() - 5) + (int) (40.0 * Math.sin(radians) + (int) (40.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                prey.directionAngle = prey.directionAngle - 30;
                prey.velocity = 2 * prey.velocity;
            }
        }
        k = ((int) prey.getX() - 5) + (int) (40.0 * Math.cos(radians) + (int) (40.0 * Math.sin(radians)));
        l = ((int) prey.getY() - 5) + (int) (40.0 * Math.sin(radians) - (int) (40.0 * Math.cos(radians)));
        if (k > 0 && l > 0 && k < 1250 && l < 850) {
            if (model[k][l] == 'P') {
                prey.directionAngle = prey.directionAngle + 30;
                prey.velocity = 2 * prey.velocity;
            }
        }
    }
}
