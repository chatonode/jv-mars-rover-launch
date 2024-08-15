package business.plateau;

public class PlateauSize {
    private final XCoords xCoordinates;
    private final YCoords yCoordinates;

    public PlateauSize(int maxXValue, int maxYValue) {
        this.xCoordinates = new XCoords(0, maxXValue);
        this.yCoordinates = new YCoords(0, maxYValue);
    }

    public XCoords getXCoordinates() {
        return this.xCoordinates;
    }

    public YCoords getYCoordinates() {
        return this.yCoordinates;
    }
}
