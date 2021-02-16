package game.organisms;

import game.stage.Stage;

import java.awt.*;

public interface Predator {

    boolean isAlive();

    void move(char[][] model, Stage stage);

    void eat();

    double getEnergy();

    double getY();

    double getX();

    void paint(Graphics2D g);
}
