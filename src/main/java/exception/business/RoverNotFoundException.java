package exception.business;

abstract class RoverNotFoundException extends NotFoundException {
    protected RoverNotFoundException(String message) {
        super(message);
    }
}
