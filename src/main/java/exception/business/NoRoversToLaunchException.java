package exception.business;

public class NoRoversToLaunchException extends RuntimeException {
    public NoRoversToLaunchException() {
        super("No rovers found on Earth to launch!");
    }
}
