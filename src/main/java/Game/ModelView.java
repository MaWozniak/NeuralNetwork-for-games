package Game;

import GUI.ModelViewGui;

class ModelView {

    private char[][] model = new char[1300][900];
    private ModelViewGui modelView;

    ModelView(boolean visible) {

        modelView = new ModelViewGui(visible, model);

    }

    char[][] getModel() {
        return model;
    }

    char getField(int x, int y) {
        return model[x][y];
    }

    void repaintGui() {
        modelView.repaint();
        modelView.revalidate();
    }

    void clear() {
        for (int i = 0; i < 1300; i++) {
            for (int j = 0; j < 900; j++) {
                model[i][j] = '0';
            }
        }
        this.paintspecialPlaces();
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

    private void paintspecialPlaces() {
        //Darker/Hide Places
        this.setRectengle(90, 400, 'M', 800, 180);
        this.setRectengle(1120, 400, 'M', 800, 180);
        //Feed Places
        this.setRectengle(425, 590, 'F', 180, 50);
        this.setRectengle(725, 210, 'F', 180, 50);

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
