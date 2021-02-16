package gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class BoardGui {

    private boolean isHidePlacesCenter;
    private boolean isHidePlacesBorder;

    public BoardGui() {
        this.isHidePlacesBorder = false;
        this.isHidePlacesCenter = false;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1200, 800);
        g.setColor(Color.WHITE);
        g.fillRect(20, 20, 1160, 760);

        if (isHidePlacesCenter) {
            g.setColor(Color.lightGray);
            g.fillRect(400, 270, 400, 280);
        }
        if (isHidePlacesBorder) {
            g.setColor(Color.lightGray);
            g.fillRect(20, 20, 1160, 760);
            g.setColor(Color.WHITE);
            g.fillRect(80, 80, 1040, 650);
            g.setColor(Color.lightGray);
            g.fillRect(400, 270, 400, 280);
        }
    }

    public boolean isHidePlacesCenter() {
        return isHidePlacesCenter;
    }

    public void setHidePlacesCenter(boolean hidePlacesCenter) {
        isHidePlacesCenter = hidePlacesCenter;
    }

    public boolean isHidePlacesBorder() {
        return isHidePlacesBorder;
    }

    public void setHidePlacesBorder(boolean hidePlacesBorder) {
        isHidePlacesBorder = hidePlacesBorder;
    }
}
