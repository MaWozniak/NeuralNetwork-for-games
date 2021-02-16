package gui;

public class FrameRateCount {

    private final int[] array = {1, 5, 10, 15, 30, 45, 60, 100, 200, 300, 500, 1000};
    private int selectedIndex = 4;
    private int selected;

    private void updateSelected() {
        selected = array[selectedIndex];
    }

    public int next() {
        if (selectedIndex < array.length - 1) {
            selectedIndex++;
        }
        updateSelected();
        return selected;
    }

    public int previous() {
        if (selectedIndex > 0) {
            selectedIndex--;
        }
        updateSelected();
        return selected;
    }

    public String getSelectedInString() {
        updateSelected();
        return Integer.toString(selected);
    }
}
