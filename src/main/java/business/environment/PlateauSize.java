package business.environment;

public class PlateauSize {
    private final XCoords xCoordinates;
    private final YCoords yCoordinates;

    PlateauSize(int maximumXValue, int maximumYValue) {
        this.xCoordinates = new XCoords(0, maximumXValue);
        this.yCoordinates = new YCoords(0, maximumYValue);
    }

    protected int getMinimumX() {
        return this.xCoordinates.getStart();
    }

    protected int getMinimumY() {
        return this.yCoordinates.getStart();
    }

    protected int getMaximumX() {
        return this.xCoordinates.getEnd();
    }

    protected int getMaximumY() {
        return this.yCoordinates.getEnd();
    }


}
