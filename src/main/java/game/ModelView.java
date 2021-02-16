package game;

import gui.ModelViewGui;
import game.stage.Stage;

public class ModelView {

    private final char[][] model = new char[1300][900];
    private final ModelViewGui modelView;

    public ModelView(boolean visible) {
        modelView = new ModelViewGui(visible, model);
    }

    char[][] getModel() {
        return model;
    }

    void repaintGui() {
        modelView.repaint();
        modelView.revalidate();
    }

    void clear(Stage stage) {
        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                model[i][j] = '0';
            }
        }
        this.paintSpecialPlaces(stage.isHidePlacesCenter(), stage.isHidePlacesBorder());
    }

    void set(int x, int y, char ch, int radius) {
        for (int a = -radius; a < radius; a++) {
            for (int b = -radius; b < radius; b++) {
                if ((x + a) >= 0 && (y + b) >= 0 && (x + a) < 1290 && (y + b) < 890) {
                    model[x + a][y + b] = ch;
                }
            }
        }
    }

    private void paintSpecialPlaces(boolean center, boolean borders) {
        if (center) {
            this.setRectengle(600, 420, 'M', 280, 400);
        }
        if (borders) {
            this.setRectengle(600, 50, 'M', 80, 1300);
            this.setRectengle(600, 780, 'M', 80, 1300);
            this.setRectengle(60, 450, 'M', 900, 80);
            this.setRectengle(1160, 450, 'M', 900, 80);
        }
    }

    private void setRectengle(int x, int y, char ch, int height, int width) {
        for (int a = -width / 2; a < width / 2; a++) {
            for (int b = -height / 2; b < height / 2; b++) {
                if ((x + a) >= 0 && (y + b) >= 0 && (x + a) < 1290 && (y + b) < 890) {
                    model[x + a][y + b] = ch;
                }
            }
        }
    }

}
