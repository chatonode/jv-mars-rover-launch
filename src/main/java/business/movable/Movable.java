package business.movable;

import business.environment.Position;

public interface Movable {
    public boolean moveTo(Position nextPosition);

    public Position getInitialPosition();

    public Position getCurrentPosition();
}
