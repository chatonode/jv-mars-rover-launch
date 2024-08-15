package business.environment;

public class PlateauSize {
    private final XCoords xCoordinates;
    private final YCoords yCoordinates;

    PlateauSize(int maximumXValue, int maximumYValue) {
        this.xCoordinates = new XCoords(0, maximumXValue);
        this.yCoordinates = new YCoords(0, maximumYValue);
    }

    public int getMaximumX() {
        return this.xCoordinates.getEnd();
    }

    public int getMaximumY() {
        return this.yCoordinates.getEnd();
    }
}
