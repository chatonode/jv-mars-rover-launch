package controller.orchestrator;

import controller.environment.Position;
import controller.movable.explorer.Rover;

final class RoverFactory extends Rover {
    RoverFactory(String name, Position initialPosition, String producedBy, Integer producedYear) {
        super(name, initialPosition, producedBy, producedYear);
    }
}
