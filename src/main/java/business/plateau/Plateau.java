package business.plateau;

import business.invader.Rover;
import common.Position;
import utils.ValidationUtils;
import validation.ParameterValidator;
import validation.ParamIsValidMap;

import java.util.Map;
import java.util.function.Predicate;

public class Plateau {
    private final PlateauSize plateauSize;
    private Map<Rover, Position> roverPositionMap;

    public Plateau(int maximumX, int maximumY) {
        ParameterValidator.validateArgs(new ParamIsValidMap() {{
            put("maximumX", checkMaximumCoordinateValidity.test(maximumX));
            put("maximumY", checkMaximumCoordinateValidity.test(maximumY));
        }});

        this.plateauSize = new PlateauSize(maximumX, maximumY);
    }

    private final Predicate<Integer> checkMaximumCoordinateValidity = ValidationUtils.checkCoordinateValidity;

    public PlateauSize getPlateauSize() {
        return plateauSize;
    }
}
