package exception.business;

import controller.movable.explorer.Rovers;

public class RoverCrashException extends RuntimeException {
    public RoverCrashException(String message, Rovers crashedRovers) {
        super(message);

        System.err.println("Say good bye to our rover friends of " + crashedRovers.getFirst() + " and " + crashedRovers.getLast() + "...");
    }
}
