package business.orchestrator;

import business.movable.explorer.Rover;

public class RoverFactory extends Rover {
    protected RoverFactory(String name, Position initialPosition, String producedBy, Integer producedYear) {
        super(name, initialPosition, producedBy, producedYear);
    }
}
