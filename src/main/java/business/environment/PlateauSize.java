package business.environment;

class PlateauSize {
    private final XCoords xCoordinates;
    private final YCoords yCoordinates;

    PlateauSize(int maximumXValue, int maximumYValue) {
        this.xCoordinates = new XCoords(0, maximumXValue);
        this.yCoordinates = new YCoords(0, maximumYValue);
    }

    int getMinimumX() {
        return this.xCoordinates.getStart();
    }

    int getMinimumY() {
        return this.yCoordinates.getStart();
    }

    int getMaximumX() {
        return this.xCoordinates.getEnd();
    }

    int getMaximumY() {
        return this.yCoordinates.getEnd();
    }
}
