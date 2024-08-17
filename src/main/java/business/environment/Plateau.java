package business.environment;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import business.movable.explorer.Explorer;
import business.movable.explorer.Rover;
import business.movable.explorer.Rovers;
import exception.business.OccupiedInitialPositionException;
import utils.ListUtils;
import utils.ValidationUtils;
import validation.ParameterValidator;
import validation.ParamIsValidMap;

public class Plateau {
    private final PlateauSize plateauSize;
    //    private final RoverPositionMap roverPositionMap;
    private final Rovers rovers;

    protected Plateau(int maximumX, int maximumY) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("maximumX", checkMaximumCoordinateValidity.test(maximumX));
            put("maximumY", checkMaximumCoordinateValidity.test(maximumY));
        }});

        this.plateauSize = new PlateauSize(maximumX, maximumY);
//        roverPositionMap = new RoverPositionMap();
        rovers = new Rovers();
    }

    // Predicates
    private final Predicate<Integer> checkMaximumCoordinateValidity = ValidationUtils.checkCoordinateValidity;
    private final BiPredicate<Rovers, Rover> checkLandingPositionIsFree = (currentRovers, rover) ->
            currentRovers.stream()
                    .noneMatch(currentRover -> currentRover.getCurrentPosition().getX() == rover.getInitialPosition().getX()
                            && currentRover.getCurrentPosition().getY() == rover.getInitialPosition().getY()
                    );

    // Functions

    public void landRoverOnPlateau(Rover rover) {
        boolean isLandingPositionFree = checkLandingPositionIsFree.test(this.rovers, rover);
        if (!isLandingPositionFree) throw new OccupiedInitialPositionException(rover.getInitialPosition());

        this.rovers.add(rover);
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
