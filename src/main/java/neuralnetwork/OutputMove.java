package neuralnetwork;

public class OutputMove {
    private boolean key1;
    private boolean key2;
    private boolean key3;
    private boolean key4;

    OutputMove(boolean key1, boolean key2, boolean key4, boolean key3) {
        this.key1 = key1;
        this.key2 = key2;
        this.key4 = key4;
        this.key3 = key3;
    }

    public boolean isKey1() {
        return key1;
    }

    public void setKey1(boolean key1) {
        this.key1 = key1;
    }

    public boolean isKey2() {
        return key2;
    }

    public void setKey2(boolean key2) {
        this.key2 = key2;
    }

    public boolean isKey3() {
        return key3;
    }

    public void setKey3(boolean key3) {
        this.key3 = key3;
    }

    public boolean isKey4() {
        return key4;
    }

    public void setKey4(boolean key4) {
        this.key4 = key4;
    }
}
