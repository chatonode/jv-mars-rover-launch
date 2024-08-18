package controller.orchestrator;

import controller.environment.Position;
import common.enums.CompassDirection;

final class PositionFactory extends Position {
    PositionFactory(int x, int y, CompassDirection facingDirection) {
        super(x, y, facingDirection);
    }
}
