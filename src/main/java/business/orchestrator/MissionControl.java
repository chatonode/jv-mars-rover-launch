package business.orchestrator;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import business.environment.Plateau;
import business.environment.Position;
import business.movable.explorer.Explorer;
import business.movable.explorer.Rover;
import common.enums.CompassDirection;
import exception.business.OccupiedInitialPositionException;
import utils.ValidationUtils;
import validation.ParamIsValidMap;
import validation.ParameterValidator;

public class MissionControl {
    private final String username;
    private final Plateau plateau;
    private final List<Rover> roversToBeLaunched;

    public MissionControl(String username, int maximumX, int maximumY) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("username", checkUsernameValidity.test(username));
        }});

        this.username = username;
        this.plateau = new Plateau(maximumX, maximumY);
        this.roversToBeLaunched = new ArrayList<>();
    }

    private static final Predicate<String> checkUsernameValidity = ValidationUtils.checkStringValidity;

    private static final Predicate<String> checkSearchFilterValidity = ValidationUtils.checkStringValidity;
    private static final BiPredicate<List<Rover>, Rover> checkRoverWithExactInitialPositionExists = (listOfRovers, candidateRover) -> listOfRovers.stream()
            .map(existingRover -> candidateRover.getInitialPosition().getX() == existingRover.getInitialPosition().getX()
                    && candidateRover.getInitialPosition().getY() == existingRover.getInitialPosition().getY()).toList().isEmpty();


    private final Predicate<Integer> checkXBoundaryValidity = expectedX -> expectedX >= this.getPlateau().getMinPlateauX()
            && expectedX <= this.getPlateau().getMaxPlateauX();
    private final Predicate<Integer> checkYBoundaryValidity = expectedY -> expectedY >= this.getPlateau().getMinPlateauY()
            && expectedY <= this.getPlateau().getMaxPlateauY();
    private final Predicate<CompassDirection> checkFacingDirectionValidity = Objects::nonNull;

    public Position createDestinationPosition(int expectedX, int expectedY, CompassDirection expectedFacingDirection) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("expectedX", checkXBoundaryValidity.test(expectedX));
            put("expectedY", checkYBoundaryValidity.test(expectedY));
            put("expectedFacingDirection", checkFacingDirectionValidity.test(expectedFacingDirection));
        }});

        return new Position(expectedX, expectedY, expectedFacingDirection);
    }

    public void addRoverToBeLaunched(Rover candidateRover) {
        boolean isInitialPositionFree = checkRoverWithExactInitialPositionExists.test(this.roversToBeLaunched, candidateRover);
        if (!isInitialPositionFree) throw new OccupiedInitialPositionException(candidateRover.getInitialPosition());

        this.roversToBeLaunched.add(candidateRover);
    }

    public boolean launchRovers(Rover roverToLaunch) {
        return this.plateau.landRoverOnPlateau(roverToLaunch);
    }

//    public boolean moveRover(Rover roverToMove, Position nextPosition) {
//
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

    public List<Rover> getLandedRovers() {
        return this.plateau.getLandedRovers();
    }

    public List<Rover> getLandedRovers(String searchFilter) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("searchFilter", checkSearchFilterValidity.test(searchFilter));
        }});

        List<Rover> filteredRovers = getLandedRovers().stream().filter(
                        rover -> rover.getId().toLowerCase().contains(searchFilter)
                                || rover.getProducedBy().toLowerCase().contains(searchFilter)
                                || rover.getProducedYear().toString().toLowerCase().contains(searchFilter)
                )
                .toList();

        return filteredRovers;
    }

}
