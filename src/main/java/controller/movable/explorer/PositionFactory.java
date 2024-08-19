package controller.movable.explorer;

import controller.environment.Position;
import common.enums.CompassDirection;

class PositionFactory extends Position {
    PositionFactory(int x, int y, CompassDirection facingDirection) {
        super(x, y, facingDirection);
    }
}
