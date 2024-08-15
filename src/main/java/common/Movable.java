package common;

public interface Movable {
    public boolean move(Position nextPosition);

    public Position getInitialPosition();

    public Position getCurrentPosition();
}
