import java.awt.Graphics2D;

class Ball {
    private double x;
    private double y;

    private UpdateMovement updateMovement = new UpdateMovement();

    private double radians;
    private int directionAngle = 0;
    private double velocity = 3.0;
    private int id;

    Ball(double xStartPos, double yStartPos, int id) {
        this.x = xStartPos;
        this.y = yStartPos;
        this.id = id;
        updateMovement.start();
    }

    void move() {

        int dirAngle = updateMovement.getDirection();
        double acceleration = updateMovement.getAcceleration();

        directionAngle += dirAngle;
        velocity += acceleration;
        radians = (Math.PI / 180) * (directionAngle);

        x += (int) (velocity * Math.cos(radians));
        y += (int) (velocity * Math.sin(radians));
    }

    void paint(Graphics2D g) {
        g.fillOval((int) x - 5, (int) y - 5, 15, 15);

        //temp direction point:
        g.fillOval(((int) x - 5) + (int) (50.0 * Math.cos(radians)), ((int) y - 5) + (int) (50.0 * Math.sin(radians)), 5, 5);
        g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(radians) - (int) (20.0 * Math.sin(radians))), ((int) y - 5) + (int) (40.0 * Math.sin(radians) + (int) (20.0 * Math.cos(radians))), 5, 5);
        g.fillOval(((int) x - 5) + (int) (40.0 * Math.cos(radians) + (int) (20.0 * Math.sin(radians))), ((int) y - 5) + (int) (40.0 * Math.sin(radians) - (int) (20.0 * Math.cos(radians))), 5, 5);

        int[] xpoints = {(int) x, (int) (x + 10 * velocity * Math.cos(radians) - 25), (int) (x + 10 * velocity * Math.cos(radians)) + 25};
        int[] ypoints = {(int) y, (int) (y + 10 * velocity * Math.sin(radians)), (int) (y + 10 * velocity * Math.sin(radians))};
        int npoints = 3;

        //g.fillPolygon(xpoints, ypoints, npoints);
    }
}