package controller.orchestrator;

import java.util.*;
import java.util.function.*;

import controller.environment.Plateau;
import controller.environment.Position;
import controller.movable.explorer.Rover;
import controller.movable.explorer.Rovers;
import common.enums.CompassDirection;
import exception.business.InvalidClassParameterException;
import exception.business.PlateauAlreadyInitializedException;
import exception.business.NoRoversToLaunchException;
import exception.business.OccupiedInitialPositionException;
import utils.ListUtils;
import utils.ValidationUtils;
import validation.ParamIsValidMap;
import validation.ParameterValidator;

public class MissionControl {
    private final String username;
    private Plateau plateau;
    private final Rovers roversOnEarth;

    public MissionControl(String username) throws InvalidClassParameterException {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("username", checkUsernameValidity.test(username));
        }});

        this.username = username.trim();
        this.roversOnEarth = new Rovers();
    }

    // Validation Predicates
    private static final Predicate<String> checkUsernameValidity = ValidationUtils.checkStringValidity;
    private static final Predicate<String> checkSearchFilterValidity = ValidationUtils.checkStringValidity;
    private final Predicate<Integer> checkXBoundaryValidity = expectedX -> expectedX >= this.getPlateau().getMinPlateauX()
            && expectedX <= this.getPlateau().getMaxPlateauX();
    private final Predicate<Integer> checkYBoundaryValidity = expectedY -> expectedY >= this.getPlateau().getMinPlateauY()
            && expectedY <= this.getPlateau().getMaxPlateauY();
    private final Predicate<CompassDirection> checkFacingDirectionValidity = Objects::nonNull;
    private final BiPredicate<Rovers, Position> checkInitialPositionIsFree = (listOfRovers, candidatePosition) -> listOfRovers.stream()
            .filter(existingRover -> candidatePosition.getX() == existingRover.getInitialPosition().getX()
                    && candidatePosition.getY() == existingRover.getInitialPosition().getY()).toList().isEmpty() && this.getPlateau().isPositionEmpty(candidatePosition);

    // Functions


    // Consumers
//    private final BiConsumer<Rovers, Rover> moveRoverToNewPosition = (rovers, roverToMove) -> rovers.stream()
//            .filter(currentRover -> Objects.equals(currentRover.getId(), roverToMove.getId()))
//            .forEach(currentRover -> this.getPlateau().moveRoverOnPlateau(currentRover));


    // Methods

    // // Getters

    public String getUsername() {
        return this.username;
    }

    protected Plateau getPlateau() {
        return this.plateau;
    }

    public Rovers getRoversOnEarth() {
        return this.roversOnEarth;
    }

    public Rovers getRoversOnEarth(String searchFilter) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("searchFilter", checkSearchFilterValidity.test(searchFilter));
        }});

        return ListUtils.Rover.getFilteredRovers.apply(this.roversOnEarth, searchFilter);
    }

    public Rovers getRoversOnMars() {
        return this.plateau.getRovers();
    }

    public Rovers getRoversOnMars(String searchFilter) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("searchFilter", checkSearchFilterValidity.test(searchFilter));
        }});

        Rovers filteredRovers = ListUtils.Rover.getFilteredRovers.apply(this.plateau.getRovers(), searchFilter);

        return filteredRovers;
    }

    // Operations

    public void initializePlateau(int maximumX, int maximumY) {
        if (this.plateau != null) throw new PlateauAlreadyInitializedException("Plateau is already initialized!");

        this.plateau = new PlateauFactory(maximumX, maximumY);
    }

    public Position createDestinationPosition(int expectedX, int expectedY, CompassDirection expectedFacingDirection) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("expectedX", checkXBoundaryValidity.test(expectedX));
            put("expectedY", checkYBoundaryValidity.test(expectedY));
            put("expectedFacingDirection", checkFacingDirectionValidity.test(expectedFacingDirection));
        }});

        return new PositionFactory(expectedX, expectedY, expectedFacingDirection);
    }

    public void addRoverToBeLaunched(String name, Position initialPosition, String producedBy, int producedYear) {
        boolean isInitialPositionFree = checkInitialPositionIsFree.test(this.roversOnEarth, initialPosition);
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
//        if (!isNewPositionValid) throw new OccupiedNextPositionException(roverToMove.getCurrentPosition());
//
//        this.plateau.moveRoverOnPlateau(roverToMove);
//    }
//
//    public boolean rotateRover(Rover roverToRotate) {
//
//    }

}
