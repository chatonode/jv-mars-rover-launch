package exception.business;

import business.movable.explorer.Rover;

import java.util.List;

public class RoverCrashException extends RuntimeException {
    public RoverCrashException(String message, List<Rover> crashedRovers) {
        super(message);

        System.err.println("Say good bye to our rover friends of " + crashedRovers.getFirst() + " and " + crashedRovers.getLast() + "...");
    }
}
