package exception.business;

import controller.environment.Position;

public class OccupiedInitialPositionException extends OccupiedPositionException {
    public OccupiedInitialPositionException(Position initialPosition) {
        super("Initial Position is already occupied!: " + initialPosition.toString());
    }
}
