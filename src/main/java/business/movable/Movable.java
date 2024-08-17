package business.movable;

import business.orchestrator.Position;

public interface Movable {
    public boolean moveTo(Position nextPosition);

    public Position getInitialPosition();

    public Position getCurrentPosition();
}
