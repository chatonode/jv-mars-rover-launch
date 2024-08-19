package exception.business;

import controller.environment.Position;

public class OccupiedNextPositionException extends OccupiedPositionException {
    public OccupiedNextPositionException(Position nextPosition) {
        super("Plateau Position is already occupied!: " + nextPosition.toString());
    }
}
