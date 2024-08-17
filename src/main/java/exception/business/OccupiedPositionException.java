package exception.business;

public class OccupiedPositionException extends RuntimeException {
    public OccupiedPositionException(String message) {
        super(message);
    }
}
