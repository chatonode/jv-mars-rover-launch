package business.orchestrator;

import common.enums.CompassDirection;

public class Position {
    private int x;
    private int y;
    private CompassDirection facingDirection;

    protected Position(int x, int y, CompassDirection facingDirection) {
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFacingDirection(CompassDirection facingDirection) {
        this.facingDirection = facingDirection;
    }

    @Override
    public String toString() {
        String str = String.format("[%d,%d] - %s",
                getX(),
                getY(),
                getFacingDirection().toReadableString());

        return str;
    }
}
