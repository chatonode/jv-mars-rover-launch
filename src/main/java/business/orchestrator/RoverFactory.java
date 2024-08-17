package business.orchestrator;

import business.environment.Position;
import business.movable.explorer.Rover;

final class RoverFactory extends Rover {
    RoverFactory(String name, Position initialPosition, String producedBy, Integer producedYear) {
        super(name, initialPosition, producedBy, producedYear);
    }
}
