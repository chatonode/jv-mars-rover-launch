package exception.business;

import business.orchestrator.Position;

public class OccupiedInitialPositionException extends OccupiedPositionException {
    public OccupiedInitialPositionException(Position initialPosition) {
        super("Initial Position is already occupied!: " + initialPosition.toString());
    }
}
