package gui;

import java.util.ArrayList;
import java.util.List;

public class FrameRateCount {

    private final List<Integer> list;
    private int selected;

    public FrameRateCount() {
        list = populateList();
    }

    public int next(int actual) {
        int actualIndex = list.indexOf(actual);
        if( actualIndex == list.size() - 1) {
            selected = actual;
        } else {
            selected = list.get(actualIndex + 1);
        }
        return selected;
    }

    public int previous(int actual) {
        int actualIndex = list.indexOf(actual);
        if ( actualIndex == 0 ) {
            selected = actual;
        } else {
            selected = list.get(actualIndex - 1);
        }
        return selected;
    }

    public String getSelectedInString() {
        return Integer.toString(selected);
    }

    private List<Integer> populateList() {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(5);
        result.add(15);
        result.add(30);
        result.add(60);
        result.add(120);
        result.add(250);
        result.add(500);
        result.add(1000);
        result.add(9999999);
        return result;
    }
}
