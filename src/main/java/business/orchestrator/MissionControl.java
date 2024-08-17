package business.orchestrator;

import java.util.*;
import java.util.function.*;

import business.environment.Plateau;
import business.environment.Position;
import business.movable.explorer.Rover;
import common.enums.CompassDirection;
import exception.business.NoRoversToLaunchException;
import exception.business.OccupiedInitialPositionException;
import utils.ListUtils;
import utils.ValidationUtils;
import validation.ParamIsValidMap;
import validation.ParameterValidator;

public class MissionControl {
    private final String username;
    private final Plateau plateau;
    private final List<Rover> roversOnEarth;

    public MissionControl(String username, int maximumX, int maximumY) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("username", checkUsernameValidity.test(username));
        }});

        this.username = username;
        this.plateau = new PlateauFactory(maximumX, maximumY);
        this.roversOnEarth = new ArrayList<>();
    }

    // Validation Predicates
    private static final Predicate<String> checkUsernameValidity = ValidationUtils.checkStringValidity;
    private static final Predicate<String> checkSearchFilterValidity = ValidationUtils.checkStringValidity;
    private final Predicate<Integer> checkXBoundaryValidity = expectedX -> expectedX >= this.getPlateau().getMinPlateauX()
            && expectedX <= this.getPlateau().getMaxPlateauX();
    private final Predicate<Integer> checkYBoundaryValidity = expectedY -> expectedY >= this.getPlateau().getMinPlateauY()
            && expectedY <= this.getPlateau().getMaxPlateauY();
    private final Predicate<CompassDirection> checkFacingDirectionValidity = Objects::nonNull;
    private static final BiPredicate<List<Rover>, Position> checkPositionIsFree = (listOfRovers, candidatePosition) -> listOfRovers.stream()
            .filter(existingRover -> candidatePosition.getX() == existingRover.getInitialPosition().getX()
                    && candidatePosition.getY() == existingRover.getInitialPosition().getY()).toList().isEmpty();

    // Functions


    // Consumers
    private final BiConsumer<RoverPositionMap, Rover> moveRoverToNewPosition = (roverPositionMap, rover) -> {
        roverPositionMap.entrySet().stream()
                .filter(roverPositionEntry -> Objects.equals(roverPositionEntry.getKey().getId(), rover.getId()))
                .forEach(roverPositionEntry -> this.getPlateau().moveRoverOnPlateau(roverPositionEntry.getKey()));
    };

    public Position createDestinationPosition(int expectedX, int expectedY, CompassDirection expectedFacingDirection) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("expectedX", checkXBoundaryValidity.test(expectedX));
            put("expectedY", checkYBoundaryValidity.test(expectedY));
            put("expectedFacingDirection", checkFacingDirectionValidity.test(expectedFacingDirection));
        }});

        return new PositionFactory(expectedX, expectedY, expectedFacingDirection);
    }

    public void addRoverToBeLaunched(String name, Position initialPosition, String producedBy, int producedYear) {

        boolean isInitialPositionFree = checkPositionIsFree.test(this.roversOnEarth, initialPosition)
                && checkPositionIsFree.test(this.plateau.getLandedRovers(), initialPosition);
        if (!isInitialPositionFree) throw new OccupiedInitialPositionException(initialPosition);

        Rover candidateRover = new RoverFactory(name, initialPosition, producedBy, producedYear);
        this.roversOnEarth.add(candidateRover);
    }

    public void launchRovers() {
        if (this.roversOnEarth.isEmpty()) throw new NoRoversToLaunchException();

        this.roversOnEarth.forEach(this.plateau::landRoverOnPlateau);

        this.roversOnEarth.clear();
    }

//    public void moveRover() {
//        boolean isNewPositionValid = checkPositionIsFree.test(this.plateau.getLandedRovers(), roverToMove);
//
//        if (!isNewPositionValid) throw new OccupiedMovePositionException(roverToMove.getCurrentPosition());
//
//        this.plateau.moveRoverOnPlateau(roverToMove);
//    }
//
//    public boolean rotateRover(Rover roverToRotate) {
//
//    }

    public String getUsername() {
        return this.username;
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public List<Rover> getRoversOnEarth() {
        return this.roversOnEarth;
    }

    public List<Rover> getRoversOnEarth(String searchFilter) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("searchFilter", checkSearchFilterValidity.test(searchFilter));
        }});

        return ListUtils.Rover.getFilteredRovers.apply(this.roversOnEarth, searchFilter);
    }

    public List<Rover> getRoversOnMars() {
        return this.plateau.getLandedRovers();
    }

    public List<Rover> getRoversOnMars(String searchFilter) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("searchFilter", checkSearchFilterValidity.test(searchFilter));
        }});

        List<Rover> filteredRovers = ListUtils.Rover.getFilteredRovers.apply(this.plateau.getLandedRovers(), searchFilter);

        return filteredRovers;
    }

}
