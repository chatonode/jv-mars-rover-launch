package business.orchestrator;

import java.util.*;
import java.util.function.*;

import business.environment.Plateau;
import business.environment.Position;
import business.movable.explorer.Rover;
import common.enums.CompassDirection;
import exception.business.OccupiedInitialPositionException;
import exception.business.OccupiedMovePositionException;
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
        this.plateau = new Plateau(maximumX, maximumY);
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
    private static final BiPredicate<List<Rover>, Rover> checkPositionIsFree = (listOfRovers, candidateRover) -> listOfRovers.stream()
            .filter(existingRover -> candidateRover.getInitialPosition().getX() == existingRover.getInitialPosition().getX()
                    && candidateRover.getInitialPosition().getY() == existingRover.getInitialPosition().getY()).toList().isEmpty();

    // Functions
    private final static BiFunction<List<Rover>, String, List<Rover>> getFilteredRovers = ((rovers, searchFilter) -> rovers.stream().filter(
                    rover -> rover.getId().toLowerCase().contains(searchFilter)
                            || rover.getProducedBy().toLowerCase().contains(searchFilter)
                            || rover.getProducedYear().toString().toLowerCase().contains(searchFilter)
            )
            .toList()
    );

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

        return new Position(expectedX, expectedY, expectedFacingDirection);
    }

    public void addRoverToBeLaunched(Rover candidateRover) {
        boolean isInitialPositionFree = checkPositionIsFree.test(this.roversOnEarth, candidateRover)
                && checkPositionIsFree.test(this.plateau.getLandedRovers(), candidateRover);
        if (!isInitialPositionFree) throw new OccupiedInitialPositionException(candidateRover.getInitialPosition());

        this.roversOnEarth.add(candidateRover);

        System.out.println(this.roversOnEarth);
        System.out.println(this.plateau.getLandedRovers());
    }

    public void addRoversToBeLaunched(List<Rover> candidateRovers) {
        candidateRovers.forEach(this::addRoverToBeLaunched);
    }

    public void launchRovers() {
        this.roversOnEarth.forEach(this.plateau::landRoverOnPlateau);

        this.roversOnEarth.clear();
    }

    public void moveRover(Rover roverToMove) {
        boolean isNewPositionValid = checkPositionIsFree.test(this.plateau.getLandedRovers(), roverToMove);

        if (!isNewPositionValid) throw new OccupiedMovePositionException(roverToMove.getCurrentPosition());

        this.plateau.moveRoverOnPlateau(roverToMove);
    }
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

    public List<Rover> getRoversToBeLaunched(String searchFilter) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("searchFilter", checkSearchFilterValidity.test(searchFilter));
        }});

        return getFilteredRovers.apply(this.roversOnEarth, searchFilter);
    }

    public List<Rover> getRoversOnMars() {
        return this.plateau.getLandedRovers();
    }

    public List<Rover> getRoversOnMars(String searchFilter) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("searchFilter", checkSearchFilterValidity.test(searchFilter));
        }});

        List<Rover> filteredRovers = getFilteredRovers.apply(this.plateau.getLandedRovers(), searchFilter);

        return filteredRovers;
    }

}
