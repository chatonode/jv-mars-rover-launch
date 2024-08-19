package controller.environment;

public class XCoords {
    private final int start;
    private final int end;

    protected XCoords(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
