package exception.business;

public class RoverNotFoundOnPlateauException extends RoverNotFoundException {
    public RoverNotFoundOnPlateauException() {
        super("Rover not found on Plateau!");
    }
}
