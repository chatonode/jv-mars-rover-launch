package business.orchestrator;

import business.environment.Position;
import common.enums.CompassDirection;

public final class PositionFactory extends Position {
    PositionFactory(int x, int y, CompassDirection facingDirection) {
        super(x, y, facingDirection);
    }
}
