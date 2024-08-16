package exception.business;

import business.environment.Position;

public class OccupiedInitialPositionException extends RuntimeException {
    public OccupiedInitialPositionException(Position initialPosition) {
        super("Initial Position is already occupied!: " + initialPosition.toString());
    }
}
