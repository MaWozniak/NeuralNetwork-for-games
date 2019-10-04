import java.awt.Graphics2D;

public class Ball {
    double x;
    double y;
    int xa = 1;
    int ya = 1;

    //czy musi miec wlasna gra??
    private Game game;

    private GetTime20 getTime20 = new GetTime20();

    double radians;
    int directionAngle = 0;
    double velocity = 3.0;

    public Ball(Game game, double xStartPos, double yStartPos) {
        this.game= game;
        this.x = xStartPos;
        this.y = yStartPos;
    }

    void move(int dirAngle, double acceleration) {
//        if (x + xa < 0)
//            xa = 1;
//        if (x + xa > game.getWidth() - 30)
//            xa = -1;
//        if (y + ya < 0)
//            ya = 1;
//        if (y + ya > game.getHeight() - 30)
//            ya = -1;

        //tu musi byc check innek klasy z threads jaka jest wartosc nowa direction itp.

        //directionAngle = (int)(10*Math.random()-5.0);
        //directionAngle += 2; // good: -2 : 2
        //directionAngle += (int)(4*Math.random() - 5);
        directionAngle += dirAngle;
        //velocity = (int)(10*Math.random()-5.0);
        velocity += acceleration;

        radians = (Math.PI / 180) * ( directionAngle);

//        x = x + xa;
//        y = y + ya;

        x += (int)(velocity * Math.cos(radians));
        y += (int)(velocity * Math.sin(radians));
    }

    public void paint(Graphics2D g) {
        g.fillOval((int)x-5, (int)y-5, 10, 10);
//        int xpoints[] = {(int)x, 145, 25};
//        int ypoints[] = {(int)y, 25, 145};

        //odpowiednie funkcje trygonometr powinny dac kierunek patrzenia
//        int xpoints[] = {(int)x, (int)x-14, (int)x-25};
//        int ypoints[] = {(int)y, (int)y-25, (int)y-14};

//        int xpoints[] = {(int)x, (int)(x+10*velocity*Math.cos(radians)), (int)(x-10*velocity*Math.cos(radians))};
//        int ypoints[] = {(int)y, (int)(y+10*velocity*Math.sin(radians)), (int)(y+10*velocity*Math.sin(radians))};

        int xpoints[] = {(int)x-5, (int)(x+10*velocity*Math.cos(radians)-25), (int)(x+10*velocity*Math.cos(radians))+25};
        int ypoints[] = {(int)y-5, (int)(y+10*velocity*Math.sin(radians)), (int)(y+10*velocity*Math.sin(radians))};
        int npoints = 3;

        g.fillPolygon(xpoints, ypoints, npoints);
       // g.fillRect((int)x,(int)y,50, 10);
    }
}