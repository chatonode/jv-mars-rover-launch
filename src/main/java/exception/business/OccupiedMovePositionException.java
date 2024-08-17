package exception.business;

import business.orchestrator.Position;

public class OccupiedMovePositionException extends OccupiedPositionException {
    public OccupiedMovePositionException(Position nextPosition) {
        super("Plateau Position is already occupied!: " + nextPosition.toString());
    }
}
