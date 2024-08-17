package exception.business;

public class RoverNotFoundOnEarthException extends RoverNotFoundException {
    public RoverNotFoundOnEarthException() {
        super("Rover not found on Earth!");
    }
}
