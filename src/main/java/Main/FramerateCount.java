package Main;

class FramerateCount {

    private int[] array = {1, 5, 10, 15, 30, 45, 60, 100, 200, 300, 500, 1000};
    private int selectedIndex = 4;
    private int selected = array[selectedIndex];

    int next() {
        if (selectedIndex < array.length - 1) {
            selectedIndex++;
        }
        updateSelected();
        return selected;
    }

    int previous() {
        if (selectedIndex > 0) {
            selectedIndex--;
        }
        updateSelected();
        return selected;
    }

    int getSelected() {
        updateSelected();
        return selected;
    }

    private void updateSelected() {
        selected = array[selectedIndex];
    }

    String getSelectedInString() {
        updateSelected();
        return Integer.toString(selected);
    }
}
