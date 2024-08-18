package business.movable;

import business.environment.Position;

public interface Movable {
    public void move();

    public Position getInitialPosition();

    public Position getCurrentPosition();

    public Position getNextPosition();
}
