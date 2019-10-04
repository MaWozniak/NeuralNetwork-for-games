import java.awt.Graphics2D;

public class Ball {
    private double x;
    private double y;

    //does it need to had own game?
    private Game game;

    private UpdateMovement updateMovement = new UpdateMovement();

    private double radians;
    private int directionAngle = 0;
    private double velocity = 3.0;

    public Ball(Game game, double xStartPos, double yStartPos) {
        this.game= game;
        this.x = xStartPos;
        this.y = yStartPos;
    }

    void move(int dirAngle, double acceleration) {

        directionAngle += dirAngle;
        velocity += acceleration;
        radians = (Math.PI / 180) * ( directionAngle);

        x += (int)(velocity * Math.cos(radians));
        y += (int)(velocity * Math.sin(radians));
    }

    public void paint(Graphics2D g) {
        g.fillOval((int)x-5, (int)y-5, 10, 10);

        int[] xpoints = {(int)x-5, (int)(x+10*velocity*Math.cos(radians)-25), (int)(x+10*velocity*Math.cos(radians))+25};
        int[] ypoints = {(int)y-5, (int)(y+10*velocity*Math.sin(radians)), (int)(y+10*velocity*Math.sin(radians))};
        int npoints = 3;

        g.fillPolygon(xpoints, ypoints, npoints);
    }
}