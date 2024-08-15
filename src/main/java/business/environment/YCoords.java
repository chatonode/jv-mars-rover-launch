package business.environment;

public class YCoords {
    private final int start;
    private final int end;

    protected YCoords(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }
}
