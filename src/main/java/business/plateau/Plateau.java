package business.plateau;

import business.invader.Rover;
import common.Position;
import utils.ValidationUtils;
import validation.ArgumentValidator;
import validation.IsValidArgumentNameMap;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Plateau {
    private PlateauSize plateauSize;
    private Map<Rover, Position> roverPositionMap;

    public Plateau(int maximumX, int maximumY) {
        ArgumentValidator.validateArgs(new IsValidArgumentNameMap() {{
            put(checkMaximumCoordinateValidity.test(maximumX), "maximumX");
            put(checkMaximumCoordinateValidity.test(maximumY), "maximumY");
        }});

        this.plateauSize = new PlateauSize(maximumX, maximumY);
    }

    private final Predicate<Integer> checkMaximumCoordinateValidity = ValidationUtils.checkCoordinateValidity;

    public PlateauSize getPlateauSize() {
        return plateauSize;
    }
}
