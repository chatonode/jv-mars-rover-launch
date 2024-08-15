package common;

import plateau.XCoords;

public class Position {
    private final int x;
    private final int y;
    private final CompassDirection facingDirection;

    public Position(int x, int y, CompassDirection facingDirection) {
        this.x = x;
        this.y = y;
        this.facingDirection = facingDirection;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public CompassDirection getFacingDirection() {
        return facingDirection;
    }
}
