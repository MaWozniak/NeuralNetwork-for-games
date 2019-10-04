public class GetTime20 extends Thread{

    public int getDirection() {
        return direction;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public int direction = 0;
    public double acceleration = 0.0;

    public void run(){

        while (true) {

            direction = (int)(20*Math.random()-10.0);
            acceleration = 0.02*((Math.random()/5)-0.1);

            System.out.println(direction);

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}