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
    }

    void set(int x, int y, char ch) {
        int r = 30;
        for (int a = -r; a < r; a++) {
            for (int b = -r; b < r; b++) {

                if ((x + a) >= 0 && (y + b) >= 0 && (x + a) < 1290 && (y + b) < 890) {
                    model[x + a][y + b] = ch;
                }

            }
        }

    }

}
