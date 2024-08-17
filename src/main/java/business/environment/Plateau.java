package business.environment;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import business.movable.explorer.Rover;
import exception.business.OccupiedInitialPositionException;
import utils.ValidationUtils;
import validation.ParameterValidator;
import validation.ParamIsValidMap;

import business.orchestrator.RoverPositionMap;

public class Plateau {
    private final PlateauSize plateauSize;
    private final RoverPositionMap roverPositionMap;

    public Plateau(int maximumX, int maximumY) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("maximumX", checkMaximumCoordinateValidity.test(maximumX));
            put("maximumY", checkMaximumCoordinateValidity.test(maximumY));
        }});

        this.plateauSize = new PlateauSize(maximumX, maximumY);
        roverPositionMap = new RoverPositionMap();
    }

    // Predicates
    private final Predicate<Integer> checkMaximumCoordinateValidity = ValidationUtils.checkCoordinateValidity;
    private final BiPredicate<RoverPositionMap, Rover> checkLandingPositionIsFree = (currentRoverPositionMap, rover) ->
            currentRoverPositionMap.entrySet().stream()
                    .noneMatch(roverPositionEntry -> roverPositionEntry.getValue().getX() == rover.getInitialPosition().getX()
                            && roverPositionEntry.getValue().getY() == rover.getInitialPosition().getY()
                    );

    // Functions

    public void landRoverOnPlateau(Rover rover) {
        boolean isLandingPositionFree = checkLandingPositionIsFree.test(this.roverPositionMap, rover);
        if (!isLandingPositionFree) throw new OccupiedInitialPositionException(rover.getInitialPosition());

        this.roverPositionMap.put(rover, rover.getInitialPosition());
    }

    public void moveRoverOnPlateau(Rover rover) {

    }

    public int getMinPlateauX() {
        return this.plateauSize.getMinimumX();
    }

    public int getMinPlateauY() {
        return this.plateauSize.getMinimumY();
    }

    public int getMaxPlateauX() {
        return this.plateauSize.getMaximumX();
    }

    public int getMaxPlateauY() {
        return this.plateauSize.getMaximumY();
    }

    public RoverPositionMap getRoverPositionMap() {
        return this.roverPositionMap;
    }

    public List<Position> getOccupiedPositions() {
        return this.roverPositionMap.values().stream().toList();
    }

    public List<Rover> getLandedRovers() {
        return this.roverPositionMap.keySet().stream().toList();
    }
}
