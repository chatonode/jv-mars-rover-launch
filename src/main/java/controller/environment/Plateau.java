package controller.environment;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import controller.movable.explorer.Explorer;
import controller.movable.explorer.Rover;
import controller.movable.explorer.Rovers;
import exception.business.OccupiedInitialPositionException;
import exception.business.OccupiedNextPositionException;
import exception.business.RoverNotFoundOnPlateauException;
import utils.ListUtils;
import utils.ValidationUtils;
import validation.ParameterValidator;
import validation.ParamIsValidMap;

public class Plateau {
    private final PlateauSize plateauSize;
    private final Rovers rovers;

    protected Plateau(int maximumX, int maximumY) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("maximumX", checkMaximumCoordinateValidity.test(maximumX));
            put("maximumY", checkMaximumCoordinateValidity.test(maximumY));
        }});

        this.plateauSize = new PlateauSize(maximumX, maximumY);
        rovers = new Rovers();
    }

    // Predicates
    private final Predicate<Integer> checkMaximumCoordinateValidity = ValidationUtils.checkCoordinateValidity;
    public static final BiPredicate<Rovers, Position> checkPositionIsFree = ((currentRovers, position) ->
            currentRovers.stream()
                    .noneMatch(currentRover -> currentRover.getCurrentPosition().getX() == position.getX()
                            && currentRover.getCurrentPosition().getY() == position.getY()
                    )
    );
//    private final Predicate<String> checkRoverExistsOnPlateauById = (roverId -> this.getRovers().stream().anyMatch(rover -> roverId.equals(rover.getId())));

    // Consumers
//    private final Consumer<String> moveRover = (roverId -> {
//        Optional<Rover> foundRover = this.findRoverById.apply(roverId);
//    });

    // Functions
    public final Function<String, Rover> findRoverById = (roverId -> {
        Optional<Rover> foundRover = this.getRovers().stream()
                .filter(rover -> Objects.equals(rover.getId(), roverId))
                .reduce((accumulator, currentRover) -> currentRover);

        if (foundRover.isEmpty()) throw new RoverNotFoundOnPlateauException();

        return foundRover.get();
    }
    );

    // Methods

    public void landRoverOnPlateau(Rover roverToLand) {
        boolean isLandingPositionFree = this.isPositionEmpty(roverToLand.getInitialPosition());
        if (!isLandingPositionFree) throw new OccupiedInitialPositionException(roverToLand.getInitialPosition());

        this.rovers.add(roverToLand);
    }

    public void moveRoverOnPlateau(String roverId) {
        Rover foundRover = findRoverById.apply(roverId);
        boolean isNextPositionFree = this.isPositionEmpty(foundRover.getNextPosition());  // PROBLEM - no next position
        if (!isNextPositionFree) throw new OccupiedNextPositionException(foundRover.getNextPosition());

        foundRover.move();
    }

    public boolean isPositionEmpty(Position targetPosition) {
        return checkPositionIsFree.test(this.rovers, targetPosition);
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

    public Rovers getRovers() {
        return this.rovers;
    }

    public Rovers getRovers(String searchFilter) {
        return ListUtils.Rover.getFilteredRovers.apply(this.rovers, searchFilter);
    }

    public List<Position> getFilledPositions() {
        return this.rovers.stream().map(Explorer::getCurrentPosition).toList();
    }


}
