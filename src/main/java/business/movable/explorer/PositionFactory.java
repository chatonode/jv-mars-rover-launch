package business.movable.explorer;

import business.environment.Position;
import common.enums.CompassDirection;

class PositionFactory extends Position {
    PositionFactory(int x, int y, CompassDirection facingDirection) {
        super(x, y, facingDirection);
    }
}
